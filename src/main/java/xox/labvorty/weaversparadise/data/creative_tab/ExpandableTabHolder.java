package xox.labvorty.weaversparadise.data.creative_tab;

import java.util.Map;

/**
 * Duck-интерфейс, реализуемый миксином CreativeModeTabMixin на CreativeModeTab.
 * Ванильный конструктор CreativeModeTab package-private (на NeoForge его патчат в public),
 * поэтому наследование, как в VortyLib, на Fabric невозможно — группы храним прямо
 * в инстансе вкладки через этот интерфейс.
 */
public interface ExpandableTabHolder {
    Map<String, ExpandableGroup> wp$getGroups();

    void wp$setGroups(Map<String, ExpandableGroup> groups);
}
