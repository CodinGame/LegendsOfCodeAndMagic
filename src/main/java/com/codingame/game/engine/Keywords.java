package com.codingame.game.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * http://dominisz.pl
 * 02.04.2018
 */
public class Keywords {

    public boolean hasBreakthrough;
    public boolean hasCharge;
    public boolean hasDrain;
    public boolean hasGuard;
    public boolean hasLethal;
    //public boolean hasRegenerate;
    public boolean hasWard;

    public boolean hasAnyKeyword() {
        return hasBreakthrough || hasCharge || hasDrain || hasGuard || hasLethal /*|| hasRegenerate*/ || hasWard;
    }

    //TODO maybe this method should return already joined String
    public List<String> getListOfKeywords() {
        List<String> keywords = new ArrayList<>();
        if (hasBreakthrough) keywords.add("Breakthrough");
        if (hasCharge) keywords.add("Charge");
        if (hasDrain) keywords.add("Drain");
        if (hasGuard) keywords.add("Guard");
        if (hasLethal) keywords.add("Lethal");
        //if (hasRegenerate) keywords.add("Regenerate");
        if (hasWard) keywords.add("Ward");
        return keywords;
    }

    public Keywords(String data) {
        hasBreakthrough = data.charAt(0) == 'B';
        hasCharge = data.charAt(1) == 'C';
        hasDrain = data.charAt(2) == 'D';
        hasGuard = data.charAt(3) == 'G';
        hasLethal = data.charAt(4) == 'L';
        //hasRegenerate = data.charAt(5) == 'R';
        hasWard = data.charAt(5) == 'W';
    }

    public Keywords(Keywords keywords) {
        hasBreakthrough = keywords.hasBreakthrough;
        hasCharge = keywords.hasCharge;
        hasDrain = keywords.hasDrain;
        hasGuard = keywords.hasGuard;
        hasLethal = keywords.hasLethal;
        //hasRegenerate = keywords.hasRegenerate;
        hasWard = keywords.hasWard;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(hasBreakthrough ? 'B' : '-');
        sb.append(hasCharge ? 'C' : '-');
        sb.append(hasDrain ? 'D' : '-');
        sb.append(hasGuard ? 'G' : '-');
        sb.append(hasLethal ? 'L' : '-');
        //sb.append(hasRegenerate ? 'R' : '-');
        sb.append(hasWard ? 'W' : '-');
        return sb.toString();
    }

}
