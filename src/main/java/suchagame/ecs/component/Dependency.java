package suchagame.ecs.component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface Dependency {
    Class<?>[] value();
}
