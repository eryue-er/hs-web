/*
 * Copyright 2019 http://www.hswebframework.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.hswebframework.web.authorization.simple;

import lombok.Getter;
import org.hswebframework.web.authorization.*;

import java.io.Serializable;
import java.util.*;

public class SimpleAuthentication implements Authentication {

    private static final long serialVersionUID = -2898863220255336528L;

    private User user;

    private List<Permission> permissions;

    private List<Dimension> dimensions;

    @Getter
    private Map<String, Serializable> attributes = new HashMap<>();

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public List<Permission> getPermissions() {
        if (permissions == null) {
            return permissions = new ArrayList<>();
        }
        return new ArrayList<>(permissions);
    }

    @Override
    public List<Dimension> getDimensions() {
        if (dimensions == null) {
            return dimensions = new ArrayList<>();
        }
        return dimensions;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Serializable> Optional<T> getAttribute(String name) {
        return Optional.ofNullable((T) attributes.get(name));
    }

    @Override
    public Map<String, Serializable> getAttributes() {
        return attributes;
    }
}
