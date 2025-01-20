package com.kd8lvt.api.codec;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

public abstract class MutatingCodec<D,E> implements Codec<D> {
    abstract E encode(D decoded);
    abstract Codec<E> baseCodec();
    public abstract D decode(E encoded);

    @Override
    public <T> DataResult<Pair<D, T>> decode(DynamicOps<T> dynamicOps, T t) {
        Pair<E,T> codecOutput = baseCodec().decode(dynamicOps,t).getOrThrow();
        E encoded = codecOutput.getFirst();
        T t2 = codecOutput.getSecond();
        D mutated = decode(encoded);
        return DataResult.success(new Pair<>(mutated,t2));
    }

    @Override
    public <T> DataResult<T> encode(D decoded, DynamicOps<T> dynamicOps, T t) {
        return baseCodec().encode(encode(decoded),dynamicOps,t);
    }
}
