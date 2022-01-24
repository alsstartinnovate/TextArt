package com.startup.textart.fragments.texttools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.startup.textart.R;
import com.startup.textart.adapter.FontAdapter;
import com.startup.textart.interfaces.FontFragmentListener;
import com.startup.textart.views.PickerLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class FontFragment extends Fragment implements FontAdapter.FontAdapterClickListener {


    RecyclerView recyclerView;


    FontAdapter fontAdapter;


    FontFragmentListener fontFragmentListener;


    List<String> fontList;

    public void onFontItemSelected(String str) {
    }

    private List<String> loadFontList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("san_regular.ttf");

        //TODO add new font here


        arrayList.add("san_regular.ttf");
        arrayList.add("aamain.otf");
        arrayList.add("AndesRoundedLight.otf");
        arrayList.add("Auther Typeface.otf");
        arrayList.add("BalihoScript.otf");
        arrayList.add("Blooming Elegant Hand1.otf");
        arrayList.add("BloomingElegant1.otf");
        arrayList.add("BreeSerif.otf");
        arrayList.add("Butcherandblock.otf");
        arrayList.add("Cucho.otf");
        arrayList.add("EnyoSerif-Medium.otf");
        arrayList.add("Fairy Tales.otf");
        arrayList.add("Fester Bold.otf");
        arrayList.add("Fetridge.otf");
        arrayList.add("FS Fabrizio.ttf");
        arrayList.add("Gotham-Medium.otf");
        arrayList.add("HapnaSlabSerif-Light.otf");
        arrayList.add("Helena.otf");
        arrayList.add("Hipsteria.ttf");
        arrayList.add("iCiel Alina.otf");
        arrayList.add("iCiel Altus.otf");
        arrayList.add("iCiel Brush Up.ttf");
        arrayList.add("iciel Cadena.ttf");
        arrayList.add("iCiel Ciaobella.otf");
        arrayList.add("iCiel Crocante.otf");
        arrayList.add("iCiel Finch bold.otf");
        arrayList.add("iCiel Grandma.otf");
        arrayList.add("iCiel Kermel.otf");
        arrayList.add("iCiel Koni Black.otf");
        arrayList.add("iCiel LachicPro.otf");
        arrayList.add("iCiel LaChicProShaded.otf");
        arrayList.add("iCiel Pacifico.otf");
        arrayList.add("iCiel Qiber.otf");
        arrayList.add("iCiel Rukola.otf");
        arrayList.add("iCiel Simplifica.otf");
        arrayList.add("iCiel TALUHLA.otf");
        arrayList.add("iCielAmerigraf.otf");
        arrayList.add("iCielBambola.otf");
        arrayList.add("iCielCadena.otf");
        arrayList.add("iCielLiebeErika.otf");
        arrayList.add("iCielLOUSIANE.otf");
        arrayList.add("iCielNovecentosans-Medium.otf");
        arrayList.add("iCielPanton-BlackItalic.otf");
        arrayList.add("iCielParisSerif-Bold.otf");
        arrayList.add("iCielPequena.otf");
        arrayList.add("iCielSoupofJustice.otf");
        arrayList.add("Kermel-regular.ttf");
        arrayList.add("Latinotype - Queulat Uni.otf");
        arrayList.add("LesFruits.ttf");
        arrayList.add("Mijas-Ultra.otf");
        arrayList.add("Nabila.ttf");
        arrayList.add("Pony.ttf");
        arrayList.add("QX_LatypeCondensed.ttf");
        arrayList.add("Sanelma.otf");
        arrayList.add("SanFranciscoDisplay-Medium.otf");
        arrayList.add("ShowcaseSans.ttf");
        arrayList.add("Smoothy-Cursive.ttf");
        arrayList.add("Smoothy-Sans.ttf");
        arrayList.add("Stringfellows.otf");
        arrayList.add("SVN-Candlescript-Pro.otf");
        arrayList.add("SVN-Goldeye Type.ttf");
        arrayList.add("SVN-Journey-Bold.otf");
        arrayList.add("SVN-Pepper-Hands.ttf");
        arrayList.add("SVN-The Carpenter Regular.otf");
        arrayList.add("UVF LH Line1 Sans Thin.ttf");
        arrayList.add("zitroneFY.otf");
        return arrayList;
    }

    public void setListener(FontFragmentListener fontFragmentListener) {
        this.fontFragmentListener = fontFragmentListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_text_font, viewGroup, false);
        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(getActivity(), 0, false);
        pickerLayoutManager.setChangeAlpha(true);
        pickerLayoutManager.setScaleDownBy(0.4f);
        pickerLayoutManager.setScaleDownDistance(0.8f);
        this.fontList = loadFontList();
        this.recyclerView = inflate.findViewById(R.id.recycler_font);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        this.fontAdapter = new FontAdapter(getActivity(), this, this.fontList);
        this.recyclerView.setAdapter(this.fontAdapter);
        new LinearSnapHelper().attachToRecyclerView(this.recyclerView);
        this.recyclerView.setLayoutManager(pickerLayoutManager);
        pickerLayoutManager.setOnScrollStopListener(i -> {
            if (FontFragment.this.fontFragmentListener != null) {
                FontFragment.this.fontFragmentListener.onFontSelected(FontFragment.this.fontList.get(i));
            }
        });


        return inflate;
    }
}
