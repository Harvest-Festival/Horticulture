![](src/main/resources/assets/harvestfestival/logo.png)

Horticulture is the crops and trees element of Harvest Festival. It adds all the crops from before as well as some fruit trees, watering can and sprinkler. It also has a seed maker that you can use to get more seeds out of your crops.

More information about Horticulture and downloads can be found on //TODO

If you have any questions, feel free to join the [Harvest Festival Discord](https://discord.gg/MRZAyze)

Adding Horticulture to your buildscript
---
Add to your build.gradle:
```gradle
repositories {
  maven {
    url 'https://maven.joshiejack.uk/'
  }
}

dependencies {
    compile fg.deobf("uk.joshiejack.penguinlib:Penguin-Lib:${minecraft_version}-${penguinlib_version}")
    compile fg.deobf("uk.joshiejack.horticulture:Horticulture:${minecraft_version}-${horticulture_version}")
}
```

`${$penguinlib_version}` can be found [here](https://maven.joshiejack.uk/uk/joshiejack/penguinlib/PenguinLib/)
`${horticulture_version}` can be found [here](https://maven.joshiejack.uk/uk/joshiejack/horticulture/Horticulture/)