package by.epam.silina.information_handling.model;

import java.util.List;

public interface Component {

    void add(Component component);

    void remove(Component component);

    Object getChild(int index);

    TextPartTypeEnum getTestEnum();

    List<Component> getComponents();

    CharSequence getSymbols();

    List<Component> getComponentsByType(TextPartTypeEnum testEnum);
}
