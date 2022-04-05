package by.epam.silina.information_handling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextPartComposite implements Component {
    private List<Component> components = new ArrayList<>();
    private TextPartTypeEnum testEnum;

    public TextPartComposite(TextPartTypeEnum testEnum) {
        this.testEnum = testEnum;
    }

    public void add(Component component) {
        components.add(component);
    }

    public void remove(Component component) {
        components.remove(component);
    }

    public Object getChild(int index) {
        return components.get(index);
    }

    public List<Component> getComponentsByType(TextPartTypeEnum testEnum) {
        return components.stream().filter(el -> el.getTestEnum().equals(testEnum)).collect(Collectors.toList());
    }

    private CharSequence getSymbols(Component component) {
        StringBuilder symbolsBuilder = new StringBuilder();
        if (component instanceof SymbolLeaf) {
            symbolsBuilder.append(((SymbolLeaf) component).getSingleSymbol());
        } else {
            component.getComponents().forEach(el -> {
                if (el instanceof SymbolLeaf) {
                    symbolsBuilder.append(((SymbolLeaf) el).getSingleSymbol());
                } else if (!el.getComponents().isEmpty()) {
                    el.getComponents().forEach(child -> symbolsBuilder.append(getSymbols(child)));
                }
            });
        }
        return symbolsBuilder.toString();
    }

    public CharSequence getSymbols() {
        return getSymbols(this);
    }

    @Override
    public String toString() {
        return "Composite{" +
                "components=" + components +
                '}';
    }
}
