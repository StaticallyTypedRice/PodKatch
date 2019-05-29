/**
 * MIT License
 *
 * Copyright (c) 2018 Ty Young
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package goliath.css.main;

import goliath.css.interfaces.FXTheme;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.Scene;

public class FXThemeManager
{
    private static final String SCALE = "-fx-theme-scale: ";
    private static final String PRIMARY_BACKGROUND = "-fx-theme-background-color: ";
    private static final String SECONDARY_BACKGROUND = "-fx-theme-background-color-alt: ";
    private static final String SEMI_COLON = ";";
    
    private final Scene scene;
    private final ObjectProperty<FXTheme> theme;
    
    public FXThemeManager(Scene sc)
    {
        scene = sc;
        scene.getRoot().getStylesheets().add("goliath/css/Goliath-Base.css");
        
        theme = new SimpleObjectProperty<>();
    }
    
    public ObjectProperty<FXTheme> themeProperty()
    {
        return theme;
    }
    
    public void setScale(double scale)
    {
        scene.getRoot().setStyle(PRIMARY_BACKGROUND + scale + SEMI_COLON);
    }
    
    public void setBackgroundColor(String hex)
    {
        scene.getRoot().setStyle(PRIMARY_BACKGROUND + hex + SEMI_COLON);
    }
    
    public void setBackgroundAltColor(String hex)
    {
        scene.getRoot().setStyle(SECONDARY_BACKGROUND + hex + SEMI_COLON);
    }
    
    public void scale(Node node, double x, double y)
    {
        node.minHeight(x*theme.get().scaleProperty().get());
        node.maxHeight(x*theme.get().scaleProperty().get());
        
        node.minWidth(y*theme.get().scaleProperty().get());
        node.maxWidth(y*theme.get().scaleProperty().get());
    }
    
    public void scaleX(Node node, double x)
    {
        node.minHeight(x*theme.get().scaleProperty().get());
        node.maxHeight(x*theme.get().scaleProperty().get());
    }
    
    public void scaleY(Node node, double y)
    {
        node.minWidth(y*theme.get().scaleProperty().get());
        node.maxWidth(y*theme.get().scaleProperty().get());
    }
}
