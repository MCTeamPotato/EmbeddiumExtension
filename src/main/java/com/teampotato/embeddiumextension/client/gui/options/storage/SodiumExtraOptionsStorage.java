package com.teampotato.embeddiumextension.client.gui.options.storage;

import com.teampotato.embeddiumextension.client.gui.SodiumExtraGameOptions;
import com.teampotato.embeddiumextension.client.SodiumExtraClientMod;
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
