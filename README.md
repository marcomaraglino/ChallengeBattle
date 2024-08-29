
---

### Commands

- **/arena** -> aliases: `/cb`, `/challengebattle`
- **/arena create**
- **/arena join <arena_id>**
- **/arena invite <player>**

### Permissions

- **challengebattle.arena.create** -> Permette di creare un'arena.
- **challengebattle.arena.gui** -> Permette di accedere alla GUI.
- **challengebattle.arena.join** -> Permette di unirsi a un'arena.
- **challengebattle.arena.leave** -> Permette di lasciare un'arena.

### Configurazione

Il plugin è completamente personalizzabile tramite il file di configurazione.

- **Materiali**: [Documentazione Material](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html)
- **Strutture**: [Documentazione Structure](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/generator/structure/Structure.html)
- **Mob**: [Documentazione EntityType](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/entity/EntityType.html)

### PlaceHolderAPI

PlaceHolderAPI limita il numero di persone che possono accedere al cloud. Tuttavia, ho creato un placeholder specifico per il plugin.

Per risolvere questo problema, utilizzo un file `.json` per memorizzare i dati dei giocatori. Questo consente di accedere facilmente ai dati tramite altri plugin.

### Placeholders

- `%challengebattle_ifplayed%`
- `%challengebattle_ifvictories%`
- `%challengebattle_bfplayed%`
- `%challengebattle_bfvictories%`
- `%challengebattle_sfplayed%`
- `%challengebattle_sfvictories%`
- `%challengebattle_dgplayed%`
- `%challengebattle_dgvictories%`
- `%challengebattle_mkplayed%`
- `%challengebattle_mkvictories%`

--- 

