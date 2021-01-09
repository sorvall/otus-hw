package ru.sorokin;

import ru.sorokin.handler.StringHandler;
import ru.sorokin.handler.collection.CollectionBuilder;
import ru.sorokin.handler.genericAndPrimitive.*;
import ru.sorokin.handler.genericArray.*;
import ru.sorokin.handler.map.MapBuilder;
import ru.sorokin.handler.primitiveArray.*;

import java.util.ArrayList;
import java.util.List;

public class jSonHandlerListBuilder {
    public static List <StringHandler> createjSonHandlerList() {
        return new ArrayList<>() {
            {
                add(new IntBuilder());
                add(new StringBuilders());
                add(new ShortBuilder());
                add(new DoubleBuilder());
                add(new FloatBuilder());
                add(new BooleanBuilder());
                add(new ByteBuilder());
                add(new CharacterBuilder());
                add(new LongBuilder());
                add(new LongArrayBuilderGeneric());
                add(new StringArrayBuilderGeneric());
                add(new ShortArrayBuilderGeneric());
                add(new DoubleArrayBuilderGeneric());
                add(new FloatArrayBuilderGeneric());
                add(new FloatArrayBuilderGeneric());
                add(new BooleanArrayBuilderGeneric());
                add(new IntArrayBuilderGeneric());
                add(new CharacterBuilder());
                add(new BooleanArrayBuilderGeneric());
                add(new MapBuilder());
                add(new CollectionBuilder());
                add(new LongArrayBuilder());
                add(new DoubleArrayBuilder());
                add(new BooleanArrayBuilder());
                add(new CharacterArrayBuilder());
                add(new ShortArrayBuilder());
                add(new FloatArrayBuilder());
                add(new ByteArrayBuilder());
            }
        };
    }
}
