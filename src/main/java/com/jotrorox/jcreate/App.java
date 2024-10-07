package com.jotrorox.jcreate;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
  private static final Logger LOGGER = Logger.getLogger(App.class.getName());

  public static void main(String[] args) {
    if (!validateArgs(args)) {
      System.out.println(getErrorMessage("Invalid arguments. Use 'help' for usage instructions."));
      return;
    }

    switch (args.length) {
      case 0:
        System.out.println(getDefaultMessage());
        break;
      case 1:
        handleSingleArgument(args[0]);
        break;
      case 3:
        basicAppCreation(args[0], args[1], args[2]);
        break;
      default:
        System.out.println(getErrorMessage("Invalid number of arguments. Use 'help' for usage instructions."));
        break;
    }
  }

  private static boolean validateArgs(String[] args) {
    if (args.length == 0 || args.length == 1 || args.length == 3) {
      return true;
    }
    return false;
  }

  private static void handleSingleArgument(String arg) {
    switch (arg) {
      case "help":
        System.out.println(getHelpMessage());
        break;
      case "create":
        interactiveAppCreation();
        break;
      case "version":
        System.out.println("JCreate v1.0");
        break;
      default:
        System.out.println(getErrorMessage("Unknown command. Use 'help' for usage instructions."));
        break;
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

      System.out.println(getSuccessMessage("Project created successfully!"));
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error creating project", e);
      System.out.println(getErrorMessage("Failed to create project. See logs for details."));
    }
  }

  private static void interactiveAppCreation() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Creating a new Maven project");

    System.out.print("Enter the groupId: ");
    String groupId = scanner.nextLine();

    System.out.print("Enter the artifactId: ");
    String artifactId = scanner.nextLine();

    System.out.print("Enter the packaging: ");
    String packaging = scanner.nextLine();

    scanner.close();

    basicAppCreation(groupId, artifactId, packaging);
  }

  private static String getDefaultMessage() {
    return "\033[1;34m" + // Blue color
      "     --- JCreate ---     \n" +
      "                         \n" +
      "     < version 1.0 >     \n" +
      "                         \n" +
      "                         \n" +
      " A simple tool made for  \n" +
      " creating Maven projects \n" +
      "                         \n" +
      " If you need help, please\n" +
      " use <program> help      \n" +
      "                         \n" +
      "                         \n" +
      "      by Jotrorox        \n" +
      "\033[0m"; // Reset color
  }

  private static String getHelpMessage() {
    return "\033[1;32m" + // Green color
      " Help message for JCreate v1.0\n" +
      "                              \n" +
      " Usage:                       \n" +
      " jcreate <groupId> <artifactId> <packaging>\n" +
      "                            \n" +
      " Example:                   \n" +
      " jcreate com.jotrorox my-app jar\n" +
      "\033[0m"; // Reset color
  }

  private static String getErrorMessage(String message) {
    return "\033[1;31m" + // Red color
      "Error: " + message +
      "\033[0m"; // Reset color
  }

  private static String getSuccessMessage(String message) {
    return "\033[1;32m" + // Green color
      message +
      "\033[0m"; // Reset color
  }
}