package org.soulmate.virtualMachine;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;

public class TestOutOfMemoryError {

    public static void main(String[] args) {
        DirectMemoryOOM();
    }

    static class OOMObject {
    }

    //-Xms20m -Xmx20m -XX:+HeapDumpOutOfMemoryError
    public static void HeapOOM() {
        ArrayList<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }

    private static int stackLength = 1;
    //-Xss128k
    public static void VMStackSOF() {
        try {
            stackLength++;
            VMStackSOF();
        } catch (Throwable e) {
            System.out.println("stack length: " + stackLength);
            e.printStackTrace();
        }
    }

    //-Xss128k
    public static void VMStackSOF(int stackLength){
        long    var1 , var2 , var3 , var4 , var5 , var6 , var7 , var8 , var9 , var10 ,
                var11, var12, var13, var14, var15, var16, var17, var18, var19, var20 ,
                var21, var22, var23, var24, var25, var26, var27, var28, var29, var30 ,
                var31, var32, var33, var34, var35, var36, var37, var38, var39, var40 ,
                var41, var42, var43, var44, var45, var46, var47, var48, var49, var50 ,
                var51, var52, var53, var54, var55, var56, var57, var58, var59, var60 ,
                var61, var62, var63, var64, var65, var66, var67, var68, var69, var70 ,
                var71, var72, var73, var74, var75, var76, var77, var78, var79, var80 ,
                var81, var82, var83, var84, var85, var86, var87, var88, var89, var90 ,
                var91, var92, var93, var94, var95, var96, var97, var98, var99, var100;

        try {
            stackLength++;
            VMStackSOF(stackLength);
        }catch (Error e){
            System.out.println("stack Length: "+stackLength);
            e.printStackTrace();
        }

                var1  = var2  = var3  = var4  = var5  = var6  = var7  = var8  = var9  = var10  =
                var11 = var12 = var13 = var14 = var15 = var16 = var17 = var18 = var19 = var20  =
                var21 = var22 = var23 = var24 = var25 = var26 = var27 = var28 = var29 = var30  =
                var31 = var32 = var33 = var34 = var35 = var36 = var37 = var38 = var39 = var40  =
                var41 = var42 = var43 = var44 = var45 = var46 = var47 = var48 = var49 = var50  =
                var51 = var52 = var53 = var54 = var55 = var56 = var57 = var58 = var59 = var60  =
                var61 = var62 = var63 = var64 = var65 = var66 = var67 = var68 = var69 = var70  =
                var71 = var72 = var73 = var74 = var75 = var76 = var77 = var78 = var79 = var80  =
                var81 = var82 = var83 = var84 = var85 = var86 = var87 = var88 = var89 = var90  =
                var91 = var92 = var93 = var94 = var95 = var96 = var97 = var98 = var99 = var100 = 0;
    }

    //-Xss2M
    private static void dontStop(){
        while (true){

        }
    }
    public static void VMStackOOM(){
        while (true){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            }).start();
        }
    }

    //-XX:PermSize=6M -XX:MaxPermSize=6M -XX:MaxMetaspaceSize=6M
    public static void RuntimeConstantPoolOOM(){
        HashSet<String> hashSet = new HashSet<>();
        long value=0;
        while (true){
            hashSet.add(String.valueOf(value++).intern());
        }
    }

    public static void RuntimeConstantPool(){
        String str1=new StringBuilder("计算机").append("科学").toString();
        System.out.println(str1.intern()==str1);

        String str2=new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);
    }

    //-XX:PermSize=10M -XX:MaxPermSize=10M
    //-XX:MetaspaceSize -XX:MaxMetaspaceSize -XX:MinMetaspaceFreeRatio -XX:MaxMetaspaceFreeRatio
    public static void JavaMethodAreaOOM(){
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            enhancer.create();
        }
    }

    //-XX:MaxDirectMemorySize=10M -Xmx20m
    public static void DirectMemoryOOM(){
        final int _1MB = 1024 * 1024;
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = null;
        try {
            unsafe = (Unsafe) field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
