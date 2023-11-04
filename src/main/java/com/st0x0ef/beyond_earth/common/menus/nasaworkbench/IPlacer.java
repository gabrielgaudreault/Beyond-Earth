package com.st0x0ef.beyond_earth.common.menus.nasaworkbench;

import com.st0x0ef.beyond_earth.common.util.Rectangle2d;

@FunctionalInterface
public interface IPlacer {
        Rectangle2d place(int index, int left, int top, int mod);
}
