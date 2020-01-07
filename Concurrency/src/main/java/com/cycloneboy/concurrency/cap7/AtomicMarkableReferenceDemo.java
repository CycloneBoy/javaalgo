package com.cycloneboy.concurrency.cap7;

import java.util.concurrent.atomic.AtomicMarkableReference;

/** Create by sl on 2020-01-07 14:50 */
public class AtomicMarkableReferenceDemo {

  public static void main(String[] args) {
    // 实例化、取当前值和 mark 值
    final Boolean initialRef = null, initialMark = false;
    final AtomicMarkableReference<Boolean> amr =
        new AtomicMarkableReference<>(initialRef, initialMark);
    System.out.println("currentValue=" + amr.getReference() + ", currentMark=" + amr.isMarked());

    // compare and set
    final Boolean newReference1 = true, newMark1 = true;
    final boolean casResult = amr.compareAndSet(initialRef, newReference1, initialMark, newMark1);
    System.out.println(
        "currentValue="
            + amr.getReference()
            + ", currentMark="
            + amr.isMarked()
            + ", casResult="
            + casResult);

    // 获取当前的值和当前的 mark 值
    boolean[] arr = new boolean[1];
    final Boolean currentValue = amr.get(arr);
    final boolean currentMark = arr[0];
    System.out.println("currentValue=" + currentValue + ", currentMark=" + currentMark);

    // 单独设置 mark 值
    final boolean attemptMarkResult = amr.attemptMark(newReference1, false);
    System.out.println(
        "currentValue="
            + amr.getReference()
            + ", currentMark="
            + amr.isMarked()
            + ", attemptMarkResult="
            + attemptMarkResult);

    // 重新设置当前值和 mark 值
    amr.set(initialRef, initialMark);
    System.out.println("currentValue=" + amr.getReference() + ", currentMark=" + amr.isMarked());

    // [不推荐使用，除非搞清楚注释的意思了] weak compare and set
    // 困惑！weakCompareAndSet 这个方法最终还是调用 compareAndSet 方法。[版本: jdk-8u191]
    // 但是注释上写着 "May fail spuriously and does not provide ordering guarantees,
    // so is only rarely an appropriate alternative to compareAndSet."
    // todo 感觉有可能是 jvm 通过方法名在 native 方法里面做了转发
    final boolean wCasResult =
        amr.weakCompareAndSet(initialRef, newReference1, initialMark, newMark1);
    System.out.println(
        "currentValue="
            + amr.getReference()
            + ", currentMark="
            + amr.isMarked()
            + ", wCasResult="
            + wCasResult);
  }
}
