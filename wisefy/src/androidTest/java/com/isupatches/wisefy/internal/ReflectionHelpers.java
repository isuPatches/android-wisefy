/*
 * This logic/class was borrowed from Robolectric.
 *
 * https://github.com/robolectric/robolectric/blob/master/shadowapi/src/main/java/org/robolectric/util/ReflectionHelpers.java
 */
package com.isupatches.wisefy.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/** Collection of helper methods for calling methods and accessing fields reflectively. */
@SuppressWarnings(value = {"unchecked", "TypeParameterUnusedInFormals", "NewApi"})
public class ReflectionHelpers {

    /**
     * Reflectively call an instance method on an object.
     *
     * @param instance Target object.
     * @param methodName The method name to call.
     * @param classParameters Array of parameter types and values.
     * @param <R> The return type.
     * @return The return value of the method.
     */
    public static <R> R callInstanceMethod(final Object instance, final String methodName, ClassParameter<?>... classParameters) {
        try {
            final Class<?>[] classes = ClassParameter.getClasses(classParameters);
            final Object[] values = ClassParameter.getValues(classParameters);

            return traverseClassHierarchy(instance.getClass(), NoSuchMethodException.class, traversalClass -> {
                Method declaredMethod = traversalClass.getDeclaredMethod(methodName, classes);
                declaredMethod.setAccessible(true);
                return (R) declaredMethod.invoke(instance, values);
            });
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof RuntimeException) {
                throw (RuntimeException) e.getTargetException();
            }
            if (e.getTargetException() instanceof Error) {
                throw (Error) e.getTargetException();
            }
            throw new RuntimeException(e.getTargetException());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <R, E extends Exception> R traverseClassHierarchy(Class<?> targetClass, Class<? extends E> exceptionClass, InsideTraversal<R> insideTraversal) throws Exception {
        Class<?> hierarchyTraversalClass = targetClass;
        while (true) {
            try {
                return insideTraversal.run(hierarchyTraversalClass);
            } catch (Exception e) {
                if (!exceptionClass.isInstance(e)) {
                    throw e;
                }
                hierarchyTraversalClass = hierarchyTraversalClass.getSuperclass();
                if (hierarchyTraversalClass == null) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private interface InsideTraversal<R> {
        R run(Class<?> traversalClass) throws Exception;
    }

    /**
     * Typed parameter used with reflective method calls.
     *
     * @param <V> The value of the method parameter.
     */
    public static class ClassParameter<V> {
        final Class<? extends V> clazz;
        final V val;

        ClassParameter(Class<? extends V> clazz, V val) {
            this.clazz = clazz;
            this.val = val;
        }

        public static <V> ClassParameter<V> from(Class<? extends V> clazz, V val) {
            return new ClassParameter<>(clazz, val);
        }

        static Class<?>[] getClasses(ClassParameter<?>... classParameters) {
            Class<?>[] classes = new Class[classParameters.length];
            for (int i = 0; i < classParameters.length; i++) {
                Class<?> paramClass = classParameters[i].clazz;
                classes[i] = paramClass;
            }
            return classes;
        }

        static Object[] getValues(ClassParameter<?>... classParameters) {
            Object[] values = new Object[classParameters.length];
            for (int i = 0; i < classParameters.length; i++) {
                Object paramValue = classParameters[i].val;
                values[i] = paramValue;
            }
            return values;
        }
    }
}