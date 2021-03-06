// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See License.txt in the project root.

package com.microsoft.alm.storage;

import com.microsoft.alm.secret.Secret;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InsecureInMemoryStore<E extends Secret> implements SecretStore<E> {

    private final ConcurrentMap<String, E> store;

    public InsecureInMemoryStore() {
        store = new ConcurrentHashMap<String, E>();
    }

    @Override
    public E get(final String key) {
        return store.get(key);
    }

    @Override
    public boolean delete(final String key) {
        if (store.containsKey(key)) {
            return store.remove(key) != null;
        }

        return true;
    }

    @Override
    public boolean add(final String key, final E secret) {
        return store.put(key, secret) != null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
