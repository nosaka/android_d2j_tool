// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.*;
import android.widget.*;
import java.util.*;
import net.wakamesoba98.sobacha.compatible.Flavor;
import net.wakamesoba98.sobacha.compatible.SystemVersion;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.database.MuteDatabase;
import net.wakamesoba98.sobacha.dialog.*;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.view.activity.base.ThemeActivity;
import net.wakamesoba98.sobacha.view.autocomplete.AutoCompleteManager;
import net.wakamesoba98.sobacha.view.listview.adapter.SelectionAdapter;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;

// Referenced classes of package net.wakamesoba98.sobacha.view.activity:
//            NfcSendActivity

public class MutePreferenceActivity extends ThemeActivity
{
    private class ModalMultipleChoicer
    {

        private void deleteItems(ListView listview)
        {
            final ArrayList itemList = new ArrayList();
            SparseBooleanArray sparsebooleanarray = listview.getCheckedItemPositions();
            for(int i = 0; i < adapter.getCount(); i++)
                if(sparsebooleanarray.get(i))
                    itemList.add(adapter.getItem(i));

            if(itemList.size() == 0)
                return;
            int j;
            if(itemList.size() > 1)
                j = 0x7f07003e;
            else
                j = 0x7f07003f;
            (listview. new ConfirmDialog() {

                public void onPositiveButtonClick()
                {
                    removeDatabase(itemList);
                    adapter.clearSelection();
                    listView.setChoiceMode(0);
                    listView.setChoiceMode(3);
                }

                final ModalMultipleChoicer this$1;
                final List val$itemList;
                final ListView val$listView;

            
            {
                this$1 = final_modalmultiplechoicer;
                itemList = list;
                listView = ListView.this;
                super();
            }
            }
).build(MutePreferenceActivity.this, 0x7f07013b, j);
        }

        private void setListener(ListView listview)
        {
            listview.setChoiceMode(3);
            listview.setMultiChoiceModeListener(new MultiChoiceListener(listview));
        }

        final MutePreferenceActivity this$0;



        private ModalMultipleChoicer()
        {
            this$0 = MutePreferenceActivity.this;
            super();
        }

    }

    private class ModalMultipleChoicer.MultiChoiceListener
        implements android.widget.AbsListView.MultiChoiceModeListener
    {

        public boolean onActionItemClicked(ActionMode actionmode, MenuItem menuitem)
        {
            menuitem.getItemId();
            JVM INSTR tableswitch 2 2: default 24
        //                       2 26;
               goto _L1 _L2
_L1:
            return true;
_L2:
            deleteItems(listView);
            if(true) goto _L1; else goto _L3
_L3:
        }

        public boolean onCreateActionMode(ActionMode actionmode, Menu menu)
        {
            menu.add(0, 2, 0, ResourceHelper.getString(_fld0, 0x7f07003d)).setIcon(0x7f02007e);
            return true;
        }

        public void onDestroyActionMode(ActionMode actionmode)
        {
            adapter.clearSelection();
        }

        public void onItemCheckedStateChanged(ActionMode actionmode, int i, long l, boolean flag)
        {
            adapter.setSelection(i, flag);
            adapter.notifyDataSetChanged();
        }

        public boolean onPrepareActionMode(ActionMode actionmode, Menu menu)
        {
            return true;
        }

        private ListView listView;
        final ModalMultipleChoicer this$1;

        ModalMultipleChoicer.MultiChoiceListener(ListView listview)
        {
            this$1 = ModalMultipleChoicer.this;
            super();
            listView = listview;
        }
    }


    public MutePreferenceActivity()
    {
    }

    private void loadMuteList()
    {
        MuteDatabase mutedatabase = new MuteDatabase(this, table);
        mutedatabase.openDatabase();
        List list = mutedatabase.getAllData();
        mutedatabase.closeDatabase();
        adapter.clear();
        String as[];
        for(Iterator iterator = list.iterator(); iterator.hasNext(); adapter.add(as[0]))
            as = (String[])iterator.next();

        adapter.notifyDataSetChanged();
    }

    private void putDatabase(String s)
    {
        MuteDatabase mutedatabase = new MuteDatabase(this, table);
        mutedatabase.openDatabase();
        mutedatabase.putString(s);
        mutedatabase.closeDatabase();
    }

    private void removeDatabase(String s)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(s);
        removeDatabase(((List) (arraylist)));
    }

    private void removeDatabase(List list)
    {
        MuteDatabase mutedatabase = new MuteDatabase(this, table);
        mutedatabase.openDatabase();
        for(list = list.iterator(); list.hasNext(); mutedatabase.deleteData((String)list.next()));
        mutedatabase.closeDatabase();
        loadMuteList();
    }

    private void setButtonProperties()
    {
        ThemeManager thememanager = new ThemeManager(this);
        ImageButton imagebutton = (ImageButton)findViewById(0x7f0e0079);
        if(Flavor.isMateCha())
            imagebutton.setImageResource(0x7f02005c);
        else
            imagebutton.setImageResource(thememanager.getThemeDrawableId(0x7f02005d));
        if(table.equals("users") || table.equals("thumbs"))
        {
            imagebutton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    view = new ScreenNameDialog() {

                        public void onPositiveButtonClick(String s)
                        {
                            if(!"".equals(s.trim()))
                            {
                                putDatabase(s);
                                loadMuteList();
                            }
                        }

                        final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                    }
;
                    view.setFriends((new AutoCompleteManager()).loadFriendsDatabase(MutePreferenceActivity.this, userAccount.getId()));
                    view.setButtonText(0x7f0700d3);
                    view.build(MutePreferenceActivity.this);
                }

                final MutePreferenceActivity this$0;

            
            {
                this$0 = MutePreferenceActivity.this;
                super();
            }
            }
);
            return;
        } else
        {
            imagebutton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    view = new EditTextDialog() {

                        public void onPositiveButtonClick(String s)
                        {
                            if(!"".equals(s.trim()))
                            {
                                putDatabase(s);
                                loadMuteList();
                            }
                        }

                        final _cls2 this$1;

            
            {
                this$1 = _cls2.this;
                super();
            }
                    }
;
                    view.setButtonText(0x7f0700d3);
                    view.build(MutePreferenceActivity.this);
                }

                final MutePreferenceActivity this$0;

            
            {
                this$0 = MutePreferenceActivity.this;
                super();
            }
            }
);
            return;
        }
    }

    private void setListViewSettings()
    {
        ListView listview = (ListView)findViewById(0x7f0e0078);
        listview.setAdapter(adapter);
        if(SystemVersion.isHoneycombOrLater())
        {
            (new ModalMultipleChoicer()).setListener(listview);
            return;
        } else
        {
            setLongClickListener(listview);
            return;
        }
    }

    private void setLongClickListener(ListView listview)
    {
        listview.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
            {
                (((String)adapter.getItem(i)). new ConfirmDialog() {

                    public void onPositiveButtonClick()
                    {
                        removeDatabase(item);
                    }

                    final _cls3 this$1;
                    final String val$item;

            
            {
                this$1 = final__pcls3;
                item = String.this;
                super();
            }
                }
).build(MutePreferenceActivity.this, 0x7f07013b, 0x7f07003f);
                return true;
            }

            final MutePreferenceActivity this$0;

            
            {
                this$0 = MutePreferenceActivity.this;
                super();
            }
        }
);
    }

    protected void onCreate(Bundle bundle)
    {
        byte byte0;
        super.onCreate(bundle);
        setContentView(0x7f03001c);
        adapter = new SelectionAdapter(this, 0x1090003);
        userAccount = ((SobaChaApplication)getApplication()).getUserAccount();
        bundle = getIntent();
        if(bundle == null || bundle.getData() == null)
        {
            finish();
            return;
        }
        table = bundle.getData().getHost();
        bundle = table;
        byte0 = -1;
        bundle.hashCode();
        JVM INSTR lookupswitch 4: default 124
    //                   -874346147: 211
    //                   111578632: 183
    //                   113318569: 169
    //                   860587528: 197;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        byte0;
        JVM INSTR tableswitch 0 3: default 156
    //                   0 225
    //                   1 239
    //                   2 253
    //                   3 267;
           goto _L6 _L7 _L8 _L9 _L10
_L6:
        setListViewSettings();
        loadMuteList();
        setButtonProperties();
        return;
_L4:
        if(bundle.equals("words"))
            byte0 = 0;
          goto _L1
_L3:
        if(bundle.equals("users"))
            byte0 = 1;
          goto _L1
_L5:
        if(bundle.equals("clients"))
            byte0 = 2;
          goto _L1
_L2:
        if(bundle.equals("thumbs"))
            byte0 = 3;
          goto _L1
_L7:
        setTitle(ResourceHelper.getString(this, 0x7f07013f));
          goto _L6
_L8:
        setTitle(ResourceHelper.getString(this, 0x7f070139));
          goto _L6
_L9:
        setTitle(ResourceHelper.getString(this, 0x7f070034));
          goto _L6
_L10:
        setTitle(ResourceHelper.getString(this, 0x7f070121));
          goto _L6
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        if(SystemVersion.isIcsOrLater())
            menu.add(0, 1, 0, ResourceHelper.getString(this, 0x7f070100));
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 1 1: default 24
    //                   1 26;
           goto _L1 _L2
_L1:
        return true;
_L2:
        menuitem = new Intent(this, net/wakamesoba98/sobacha/view/activity/NfcSendActivity);
        menuitem.putExtra("database", "mute");
        menuitem.putExtra("table", table);
        startActivity(menuitem);
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static final int MENU_DELETE = 2;
    private static final int MENU_NFC = 1;
    private SelectionAdapter adapter;
    private String table;
    private UserAccount userAccount;






}
