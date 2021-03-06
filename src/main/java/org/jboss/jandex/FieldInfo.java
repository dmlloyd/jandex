/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.jandex;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a field.
 *
 * <p><b>Thread-Safety</b></p>
 * This class is immutable and can be shared between threads without safe publication.
 *
 * @author Jason T. Greene
 *
 */
public final class FieldInfo implements AnnotationTarget {
    private ClassInfo clazz;
    private FieldInternal internal;

    FieldInfo() {
    }

    FieldInfo(ClassInfo clazz, FieldInternal internal) {
        this.clazz = clazz;
        this.internal = internal;
    }

    FieldInfo(ClassInfo clazz, byte[] name, Type type, short flags) {
        this(clazz, new FieldInternal(name, type, flags));
    }

    /**
     * Construct a new mock Field instance.
     *
     * @param clazz the class declaring the field
     * @param name the name of the field
     * @param type the Java field type
     * @param flags the field attributes
     * @return a mock field
     */
    public static FieldInfo create(ClassInfo clazz, String name, Type type, short flags) {
         if (clazz == null)
             throw new IllegalArgumentException("Clazz can't be null");

         if (name == null)
             throw new IllegalArgumentException("Name can't be null");

        return new FieldInfo(clazz, Utils.toUTF8(name), type, flags);
    }


    /**
     * Returns the local name of the field
     *
     * @return the local name of the field
     */
    public final String name() {
        return internal.name();
    }

    /**
     * Returns the class which declared the field
     *
     * @return the declaring class
     */
    public final ClassInfo declaringClass() {
        return clazz;
    }

    /**
     * Returns the <code>Type</code> declared on this field. This may be an array, a primitive, or a generic
     * type definition.
     *
     * @return the type of this field
     */
    public final Type type() {
        return internal.type();
    }

    public final Kind kind() {
        return Kind.FIELD;
    }

    /**
     * Returns the list of annotation instances declared on this field. It may be empty, but never null.
     *
     * @return the list of annotations on this field
     */
    public List<AnnotationInstance> annotations() {
        return internal.annotations();
    }

    /**
     * Returns the access fields of this field. {@link Modifier} can be used on this value.
     *
     * @return the access flags of this field
     */
    public final short flags() {
        return internal.flags();
    }

    public String toString() {
        return internal.toString(clazz);
    }

    void setType(Type type) {
        internal.setType(type);
    }

    void setAnnotations(List<AnnotationInstance> annotations) {
        internal.setAnnotations(annotations);
    }

    FieldInternal fieldInternal() {
        return internal;
    }

    void setFieldInternal(FieldInternal internal) {
        this.internal = internal;
    }

    void setClassInfo(ClassInfo clazz) {
        this.clazz = clazz;
    }
}
