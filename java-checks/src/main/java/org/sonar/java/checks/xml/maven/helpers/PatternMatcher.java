/*
 * SonarQube Java
 * Copyright (C) 2012-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.java.checks.xml.maven.helpers;

import org.sonar.maven.model.LocatedAttribute;

import javax.annotation.Nullable;

import java.util.regex.Pattern;

public class PatternMatcher implements LocatedAttributeMatcher {

  private final Pattern pattern;

  public PatternMatcher(String regex) {
    this.pattern = compileRegex(regex);
  }

  @Override
  public boolean test(@Nullable LocatedAttribute attribute) {
    return attribute != null && pattern.matcher(attribute.getValue()).matches();
  }

  private static Pattern compileRegex(String regex) {
    try {
      return Pattern.compile(regex, Pattern.DOTALL);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Unable to compile the regular expression: " + regex, e);
    }
  }

}
