/*
 * Copyright (c) 2009-present the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *
 * The Apache License v2.0 is available at
 *   http://www.apache.org/licenses/LICENSE-2.0.html
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package com.planet57.maven.shell.commands.maven;

import java.io.File;

import com.planet57.gshell.command.support.CommandTestSupport;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the {@link MavenCommand}.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 */
public class MavenCommandTest
    extends CommandTestSupport
{
  public MavenCommandTest() {
    super(MavenCommand.class);
  }

  @Override
  @Test
  @Ignore
  public void testDefault() throws Exception {
    // disabled
  }

  @Test
  public void test1() throws Exception {
    String settings = new File(getClass().getResource("settings.xml").toURI()).toString();
    System.out.println("Settings: " + settings);

    String pom = new File(getClass().getResource("test1.pom").toURI()).toString();
    System.out.println("POM: " + pom);

    //File repoDir = new File(new File(System.getProperty("basedir")), "target/test-repo");
    //System.out.println("Repo Dir: " + repoDir);

    Object result = executeWithArgs(
        "-B", "-e", "-V",
        "-f", pom,
        "-s", settings,
        //"-Dmaven.repo.local=" + repoDir,
        "package");

    System.out.println("OUT: " + getIo().getOutputString());
    System.out.println("ERR: " + getIo().getErrorString());

    assertEquals(0, result);
  }

  /**
   * Customized help test as this isn't using the default help support by gshell.
   */
  @Override
  @Test
  public void testHelp() throws Exception {
    Object result;

    result = executeWithArgs("--help");
    assertEquals(0, result);

    result = executeWithArgs("-h");
    assertEquals(0, result);
  }
}