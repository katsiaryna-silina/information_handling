package by.epam.silina.information_handling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static by.epam.silina.information_handling.config.Constant.ADD_DOING_NOTHING;
import static by.epam.silina.information_handling.config.Constant.REMOVE_DOING_NOTHING;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SymbolLeaf implements Component {
    private Character singleSymbol;
    private TextPartTypeEnum testEnum = TextPartTypeEnum.SYMBOL;

    public SymbolLeaf(Character singleSymbol) {
        this.singleSymbol = singleSymbol;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "singleSymbol=" + singleSymbol +
                '}';
    }

    @Override
    public void add(Component component) {
        log.info(ADD_DOING_NOTHING);
    }

    @Override
    public void remove(Component component) {
        log.info(REMOVE_DOING_NOTHING);
    }

    @Override
    public Object getChild(int index) {
        return null;
    }

    @Override
    public List<Component> getComponents() {
        return null;
    }

    @Override
    public CharSequence getSymbols() {
        return null;
    }

    @Override
    public List<Component> getComponentsByType(TextPartTypeEnum testEnum) {
        return null;
    }
}
