package me.flashyreese.mods.embeddiumextension.client.gui.options.storage;

import me.flashyreese.mods.embeddiumextension.client.SodiumExtraClientMod;
import me.flashyreese.mods.embeddiumextension.client.gui.SodiumExtraGameOptions;
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;

public class SodiumExtraOptionsStorage implements OptionStorage<SodiumExtraGameOptions> {
    private final SodiumExtraGameOptions options = SodiumExtraClientMod.options();

    @Override
    public SodiumExtraGameOptions getData() {
        return this.options;
    }

    @Override
    public void save() {
        this.options.writeChanges();
    }
}
