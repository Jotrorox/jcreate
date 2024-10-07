package com.jotrorox.jcreate;

public class App {
  public static void main(String[] args) {
    if (args.length < 3) {
      System.out.println("Usage: java MavenProjectCreator <groupId> <artifactId> <packaging>");
      System.out.println("Example: java MavenProjectCreator com.example myproject jar");
      return;
    }

    String groupId = args[0];
    String artifactId = args[1];
    String packaging = args[2];

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
}
