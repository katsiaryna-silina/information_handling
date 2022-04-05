package by.epam.silina.information_handling.util.finder;

import by.epam.silina.information_handling.model.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class TextComponentFinder {
    private TextComponentFinder nextFinder;

    public abstract void parseContentToComposite(String context, Component compositeObject);

    public Component find(String context, Component compositeObject) {
        if (nextFinder != null) {
            nextFinder.parseContentToComposite(context, compositeObject);
        }
        return compositeObject;
    }
}
