package Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionHelper {
    private static final boolean DEBUG_MODE = Main.Main.getDebugMode();

    public static Object execute(Class root, Object invoker, String function, Object[] args) {
        // Use reflexion to invoke any method declared for root class
        Method fun;
        try {
            if (args == null) {
                fun = root.getDeclaredMethod(function);

                return fun.invoke(invoker);
            } else {
                fun = root.getDeclaredMethod(function,
                        getParameterTypes(root, function));

                return fun.invoke(invoker, args);
            }

        } catch (NoSuchMethodException e) {
            if (DEBUG_MODE) e.printStackTrace();
        } catch (IllegalAccessException e) {
            if (DEBUG_MODE) e.printStackTrace();
        } catch (InvocationTargetException e) {
            if (DEBUG_MODE) e.printStackTrace();
        }
        return null;
    }

    private static Class<?>[] getParameterTypes(Class<? extends Object> aClass, String methodName) {
        try {
            for (Method m : aClass.getDeclaredMethods()) {
                if (m.getName().equals(methodName)) {
                    return m.getParameterTypes();
                }
            }

        } catch (Exception ex) {
            if (DEBUG_MODE) ex.printStackTrace();
        }

        // If nothing have been found return null value
        return null;
    }

}
