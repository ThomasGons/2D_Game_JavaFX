package suchagame.ui;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import suchagame.ecs.component.GraphicComponent;
import suchagame.ecs.component.TransformComponent;
import suchagame.utils.Vector2f;

/**
 * Class that handles the light effect.
 */
public class Light {
    private static final int shimmeringRadius = 7;
    private static final int shimmeringDelta = 80;
    private static final Stop[] keyStops = new Stop[] {
            new Stop(0.75, Color.rgb(255, 235, 133, 0.12)),
            new Stop(1.0, Color.BLACK)
    };
    public static int defaultRadius = 350;
    private static int radius = defaultRadius;
    private static long lastTimeShimmering = System.currentTimeMillis();

    /**
     * Method that renders the light effect with a radial gradient and a blend effect.
     * Shimmering effect is done by changing the radius of the gradient in a random range.
     */
    public static void shimmeringLight() {
        if (System.currentTimeMillis() -  lastTimeShimmering > shimmeringDelta) {
            radius = defaultRadius + (int) (Math.random() * shimmeringRadius - shimmeringRadius / 2);
            lastTimeShimmering = System.currentTimeMillis();
        }
        Vector2f virtualPosition = Game.em.getPlayer().getComponent(TransformComponent.class).getVirtualPosition();
        GraphicComponent graphicComponent = Game.em.getPlayer().getComponent(GraphicComponent.class);

        // radial gradient around the player with a blend effect
        RadialGradient gradient = new RadialGradient(
                0,
                0,
                virtualPosition.getX() + graphicComponent.getWidth() * Camera.scale / 2,
                virtualPosition.getY() + (graphicComponent.getHeight() + 15) * Camera.scale / 2,
                radius,
                false,
                CycleMethod.NO_CYCLE,
                keyStops
        );
        Blend blend = new Blend(
                BlendMode.SRC_ATOP,
                null,
                new ColorInput(
                        0, 0,
                        Game.width, Game.height,
                        gradient
                )
        );
        Game.canvas.setEffect(blend);
    }
}
