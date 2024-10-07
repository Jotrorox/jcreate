package com.jotrorox.jcreate;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    switch (args.length) {
      case 0: {
        System.out.println(getDefaultMessage());
        break;
      }

      case 1: {
        if (args[0].equals("help")) System.out.println(getHelpMessage());
        else if (args[0].equals("create")) interactiveAppCreation();
        else if (args[0].equals("version")) System.out.println("JCreate v1.0");
        else System.out.println(getDefaultMessage());
        break;
      }

      case 3: {
        basicAppCreation(args[0], args[1], args[2]);
        break;
      }

      default: {
        System.out.println(getDefaultMessage());
        break;
      }
    }
  }

  private static void basicAppCreation(String groupId, String artifactId, String packaging) {
    try {
      ProcessBuilder pb = new ProcessBuilder("mvn", "archetype:generate",
        "-DgroupId=" + groupId,
        "-DartifactId=" + artifactId,
        "-DarchetypeArtifactId=maven-archetype-quickstart",
        "-DinteractiveMode=false",
        "-DarchetypeVersion=1.5",
        "-Dpackaging=" + packaging);

      pb.inheritIO();
      pb.start().waitFor();

      System.out.println("Project created successfully!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void interactiveAppCreation() {
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("Creating a new Maven project");

    System.out.println("Enter the groupId:");
    String groupId = scanner.nextLine();

    System.out.println("Enter the artifactId:");
    String artifactId = scanner.nextLine();

    System.out.println("Enter the packaging:");
    String packaging = scanner.nextLine();

    scanner.close();

    basicAppCreation(groupId, artifactId, packaging);
  }

  private static String getDefaultMessage() {
    StringBuilder builder = new StringBuilder();
    builder.append("     --- JCreate ---     \n");
    builder.append("                         \n");
    builder.append("     < version 1.0 >     \n");
    builder.append("                         \n");
    builder.append("                         \n");
    builder.append(" A simple tool made for  \n");
    builder.append(" creating Maven projects \n");
    builder.append("                         \n");
    builder.append(" If you need help, please\n");
    builder.append(" use <program> help      \n");
    builder.append("                         \n");
    builder.append("                         \n");
    builder.append("      by Jotrorox        \n");
    
    return builder.toString();
  }

  private static String getHelpMessage() {
    StringBuilder builder = new StringBuilder();

    builder.append(" Help message for JCreate v1.0\n");
    builder.append("                              \n");
    builder.append(" Usage:                       \n");
    builder.append(" jcreate <groupId> <artifactId> <packaging>\n");
    builder.append("                            \n");
    builder.append(" Example:                   \n");
    builder.append(" jcreate com.jotrorox my-app jar\n");

    return builder.toString();
  }
}
