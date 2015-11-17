package com.appspot.pistatium.ssmemo.adapters;

import com.appspot.pistatium.ssmemo.models.Memo;

/**
 * Created by kimihiro on 15/11/17.
 */
public interface MemoCellInterface {
    void onClickFav(Memo memo);
    void onClickDelete(Memo memo);
}
