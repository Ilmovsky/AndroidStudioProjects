package com.lexa.mymaps;

import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.tileprovider.MapTileProviderArray;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;

import java.io.File;
import java.util.Collections;

/**
 * Created by Lexa on 01.02.2016.
 */
public class MFTileProvider extends MapTileProviderArray {

    public MFTileProvider(IRegisterReceiver receiverRegistrar, File file) {

        super(MFTileSource.createFromFile(file), receiverRegistrar);

        // Create the module provider; this class provides a TileLoader that
        // actually loads the tile from the DB.
        MFTileModuleProvider moduleProvider;
        moduleProvider = new MFTileModuleProvider(receiverRegistrar, file, (MFTileSource) getTileSource());

        MapTileModuleProviderBase[] pTileProviderArray;
        pTileProviderArray = new MapTileModuleProviderBase[] { moduleProvider };

        // Add the module provider to the array of providers; mTileProviderList
        // is defined by the superclass.
        Collections.addAll(mTileProviderList, pTileProviderArray);
    }

}
