/*
 * MIT License
 *
 * Copyright (c) 2021 Hasan Demirtaş
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.portlek.reflection;

/**
 * an interface to determine modifiable objects.
 */
public interface RefModifiable {

  /**
   * checks if the field has {@code final} modifier.
   *
   * @return {@code true} if the field has {@code final} modifier.
   */
  boolean hasFinal();

  /**
   * checks if the field has {@code private} modifier.
   *
   * @return {@code true} if the field has {@code private} modifier.
   */
  boolean hasPrivate();

  /**
   * checks if the field has {@code public} modifier.
   *
   * @return {@code true} if the field has {@code public} modifier.
   */
  boolean hasPublic();

  /**
   * checks if the field has {@code static} modifier.
   *
   * @return {@code true} if the field has {@code static} modifier.
   */
  boolean hasStatic();
}
