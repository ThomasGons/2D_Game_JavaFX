package suchagame.ecs.entity;

import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.input.KeyCode;
import suchagame.ecs.component.*;
import suchagame.ecs.component.AnimationComponent.ACTION;
import suchagame.ui.Game;

import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {
    public Player() {
        super();
        this.addComponent(new TransformComponent(700, 550));
        this.addComponent(new InputComponent(
                new KeyCode[]{KeyCode.Z, KeyCode.Q, KeyCode.S, KeyCode.D},
                new KeyCode[]{
                        KeyCode.AMPERSAND, KeyCode.DEAD_ACUTE, KeyCode.QUOTEDBL,
                        KeyCode.B, KeyCode.E, KeyCode.A
                }
        ));

        this.addComponent(new GraphicComponent("flame.png"));
        this.addComponent(new AnimationComponent(
                this.getComponent(GraphicComponent.class),
                12, ACTION.IDLE, 0,
                new int[]{8, 8}
        ));

        this.addComponent(new PhysicComponent(
                25, 35, -12, -8,
                100));

        this.addComponent(new StatsComponent(
                new HashMap<>(Map.of(
                    "hp_max", 100f,
                    "mp_max", 100f,
                    "atk", 5f,
                    "def", 10f,
                    "spd", 2f
                )), new HashMap<>(Map.of(
                    "hp", new SimpleFloatProperty(100f),
                    "mp", new SimpleFloatProperty(100f)
                ))
        ));

        this.addComponent(new InventoryComponent(new HashMap<>(Map.of(
                Game.em.getItem("slime_drop"), 100,
                Game.em.getItem("fireball"), 1,
                Game.em.getItem("heal_potion"), 3,
                Game.em.getItem("mana_potion"), 2
            ))
        ));

        this.addComponent(new GameplayComponent(new HashMap<>(Map.of(
                Item.ItemType.SPELL, Game.em.getItem("fireball"),
                Item.ItemType.CONSUMABLE, Game.em.getItem("heal_potion")
            )))
        );
    }
}
