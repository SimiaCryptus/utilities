package com.simiacryptus.util.lang;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import de.javakaffee.kryoserializers.EnumMapSerializer;
import de.javakaffee.kryoserializers.EnumSetSerializer;
import de.javakaffee.kryoserializers.KryoReflectionFactorySupport;

import java.util.EnumMap;
import java.util.EnumSet;

public class KryoUtil {

    private static final ThreadLocal<Kryo> threadKryo = new ThreadLocal<Kryo>() {

        @Override
        protected Kryo initialValue() {
            final Kryo kryo = new KryoReflectionFactorySupport() {

                @Override
                public Serializer<?> getDefaultSerializer(@SuppressWarnings("rawtypes") final Class clazz) {
                    if (EnumSet.class.isAssignableFrom(clazz))
                        return new EnumSetSerializer();
                    if (EnumMap.class.isAssignableFrom(clazz))
                        return new EnumMapSerializer();
                    return super.getDefaultSerializer(clazz);
                }

            };
            return kryo;
        }

    };

    public static Kryo kryo() {
        return threadKryo.get();
    }
}
