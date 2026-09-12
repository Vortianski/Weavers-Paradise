package xox.labvorty.weaversparadise.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Мини-замена NeoForge ModConfigSpec: JSON в папке config/, API — поле.get(). */
public final class WPConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private final Path path;
    private final Map<String, Value<?>> values = new LinkedHashMap<>();

    private WPConfig(Path path) {
        this.path = path;
    }

    public static WPConfig create(String fileName) {
        WPConfig config = new WPConfig(FabricLoader.getInstance().getConfigDir().resolve(fileName + ".json"));
        config.load();
        return config;
    }

    public Value<Boolean> registerBool(String key, boolean defaultValue) {
        Value<Boolean> value = new Value<>(this, key, defaultValue, null, null);
        this.values.put(key, value);
        return value;
    }

    public Value<Double> registerDouble(String key, double defaultValue, double min, double max) {
        Value<Double> value = new Value<>(this, key, defaultValue, min, max);
        this.values.put(key, value);
        return value;
    }

    public Value<List<String>> registerStringList(String key, List<String> defaultValue) {
        Value<List<String>> value = new Value<>(this, key, defaultValue, null, null);
        this.values.put(key, value);
        return value;
    }

    public void load() {
        if (Files.exists(this.path)) {
            try {
                JsonObject file = GSON.fromJson(Files.readString(this.path), JsonObject.class);
                if (file != null) {
                    for (Map.Entry<String, JsonElement> e : file.entrySet()) {
                        Value<?> v = this.values.get(e.getKey());
                        if (v != null) v.fromJson(e.getValue());
                    }
                }
            } catch (Exception ex) {
                xox.labvorty.weaversparadise.WeaversParadise.LOGGER.warn("Failed to read config {}", this.path, ex);
            }
        }
        this.save();
    }

    public void save() {
        JsonObject root = new JsonObject();
        for (Value<?> v : this.values.values()) root.add(v.key, v.toJson());
        try {
            Files.createDirectories(this.path.getParent());
            Files.writeString(this.path, GSON.toJson(root));
        } catch (IOException ex) {
            xox.labvorty.weaversparadise.WeaversParadise.LOGGER.warn("Failed to write config {}", this.path, ex);
        }
    }

    void notifyChange() {
        this.save();
    }

    public static final class Value<T> {
        private final WPConfig owner;
        private final String key;
        private final T defaultValue;
        private final Double min;
        private final Double max;
        private T value;

        private Value(WPConfig owner, String key, T defaultValue, Double min, Double max) {
            this.owner = owner;
            this.key = key;
            this.defaultValue = defaultValue;
            this.min = min;
            this.max = max;
            this.value = defaultValue;
        }

        /** Аналог ModConfigSpec.ConfigValue#get() */
        public T get() {
            return this.value;
        }

        public void set(T newValue) {
            this.value = clamp(newValue);
            this.owner.notifyChange();
        }

        /** Аналог ModConfigSpec.ConfigValue#save() */
        public void save() {
            this.owner.notifyChange();
        }

        @SuppressWarnings("unchecked")
        private T clamp(T input) {
            if (this.min != null && this.max != null && input instanceof Number number) {
                double d = number.doubleValue();
                d = Math.max(this.min, Math.min(this.max, d));
                return (T) (Double) d;
            }
            return input;
        }

        JsonElement toJson() {
            Object v = this.value;
            if (v instanceof Boolean b) return new JsonPrimitive(b);
            if (v instanceof Number n) return new JsonPrimitive(n);
            if (v instanceof List<?> list) {
                JsonArray array = new JsonArray();
                for (Object o : list) array.add(String.valueOf(o));
                return array;
            }
            return new JsonPrimitive(String.valueOf(v));
        }

        @SuppressWarnings("unchecked")
        void fromJson(JsonElement element) {
            if (element == null || element.isJsonNull()) return;
            if (this.defaultValue instanceof Boolean) {
                if (element.getAsJsonPrimitive().isBoolean()) this.value = (T) (Boolean) element.getAsBoolean();
            } else if (this.defaultValue instanceof Double) {
                if (element.getAsJsonPrimitive().isNumber()) this.value = clamp((T) (Double) element.getAsDouble());
            } else if (this.defaultValue instanceof List) {
                List<String> list = new ArrayList<>();
                if (element.isJsonArray()) {
                    element.getAsJsonArray().forEach(e -> list.add(e.getAsString()));
                }
                this.value = (T) list;
            }
        }
    }
}
