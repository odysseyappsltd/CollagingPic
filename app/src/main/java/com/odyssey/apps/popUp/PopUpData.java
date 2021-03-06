package com.odyssey.apps.popUp;

import android.graphics.Color;

import com.odyssey.apps.collagingpic.R;

/**
 * Created by admin on 2018-03-15.
 */

public class PopUpData {

    // Singleton Structure
    private static final PopUpData instance = new PopUpData();
    //private constructor to avoid client applications to use constructor
    private PopUpData(){}
    public static PopUpData getSharedInstance(){
        return instance;
    }


    //round picker

    private int roundValue = 0;
    public int getRoundValue(){return roundValue; }
    public void saveRoundValue(int value){this.roundValue = value ;}


    //round picker FreeStyle

    private int roundValueFreeStyle = 0;
    public int getRoundValueFreeStyle(){return roundValueFreeStyle; }
    public void saveRoundValueFreeStyle(int value){this.roundValueFreeStyle = value ;}

    //shade picker

    private int shadeValue = 0;
    public int getShadeValue(){return shadeValue; }
    public void saveShadeValue(int value){this.shadeValue = value ;}

    //shade picker FreeStyle

    private int shadeValueFreeStyle = 0;
    public int getShadeValueFreeStyle(){return shadeValueFreeStyle; }
    public void saveShadeValueFreeStyle(int value){this.shadeValueFreeStyle = value ;}

    //Shape picker

    private int shrinkValue = 0;
    public int getShrinkValue(){return shrinkValue; }
    public void saveShrinkValue(int value){this.shrinkValue = value ;}

    //Shape picker FreeStyle
    private int shrinkValueFreeStyle = 0;
    public int getShrinkValueFreeStyle(){return shrinkValueFreeStyle; }
    public void saveShrinkValueFreeStyle(int value){this.shrinkValueFreeStyle = value ;}

    // ColorPicker
    private int color = Color.WHITE;
    public int getColor(){
        return  color ;
    }
    public void saveColor(int color){
        this.color = color;
    }

    // ColorPicker FreeStyle
    private int colorFreeStyle = Color.WHITE;
    public int getColorFreeStyle(){
        return  colorFreeStyle ;
    }
    public void saveColorFreeStyle(int color){
        this.colorFreeStyle = color;
    }

    //Pattern Picker
    private int pattern = R.drawable.p1 ;
    public int getPattern(){
        return pattern;
    }
    public void savePatternForIndex(int index){
        this.pattern = getPatternsAt(index);
    }




    //Patterns
    private int patterns[] = new int[]{

     R.drawable.p1 ,
     R.drawable.p2 ,
     R.drawable.p3 ,
     R.drawable.p4 ,
     R.drawable.p5 ,
     R.drawable.p6 ,
     R.drawable.p7 ,
     R.drawable.p8 ,
     R.drawable.p9 ,
     R.drawable.p10 ,
     R.drawable.p11 ,
     R.drawable.p12 ,
     R.drawable.p13 ,
     R.drawable.p14 ,
     R.drawable.p15 ,
     R.drawable.p16 ,
     R.drawable.p17 ,
     R.drawable.p18 ,
     R.drawable.p19 ,
     R.drawable.p20 ,
     R.drawable.p21 ,
     R.drawable.p22 ,
     R.drawable.p23 ,
     R.drawable.p24 ,
     R.drawable.p25 ,
     R.drawable.p26 ,
     R.drawable.p27 ,
     R.drawable.p28 ,
     R.drawable.p29 ,
     R.drawable.p30 ,
     R.drawable.p31 ,
     R.drawable.p32 ,
     R.drawable.p33 ,
     R.drawable.p34 ,
     R.drawable.p35 ,
     R.drawable.p36 ,
     R.drawable.p37 ,
     R.drawable.p38 ,
     R.drawable.p39 ,
     R.drawable.p40 ,
     R.drawable.p41 ,
     R.drawable.p42 ,
     R.drawable.p43 ,
     R.drawable.p44 ,
     R.drawable.p45 ,
     R.drawable.p46 ,
     R.drawable.p47 ,
     R.drawable.p48 ,
     R.drawable.p49 ,
     R.drawable.p50 ,
     R.drawable.p51 ,
     R.drawable.p52 ,
     R.drawable.p53 ,
     R.drawable.p54 ,
     R.drawable.p55 ,
     R.drawable.p56 ,
     R.drawable.p57 ,
     R.drawable.p58 ,
     R.drawable.p59 ,
     R.drawable.p60 ,
     R.drawable.p61 ,
     R.drawable.p62 ,
     R.drawable.p63 ,
     R.drawable.p64 ,
     R.drawable.p65 ,
     R.drawable.p66 ,
     R.drawable.p67 ,
     R.drawable.p68 ,
     R.drawable.p69 ,
     R.drawable.p70 ,
     R.drawable.p71 ,
     R.drawable.p72 ,
     R.drawable.p73 ,
     R.drawable.p74 ,
     R.drawable.p75 ,
     R.drawable.p76 ,
     R.drawable.p77 ,
     R.drawable.p78 ,
     R.drawable.p79 ,
     R.drawable.p80 ,
     R.drawable.p81 ,
     R.drawable.p82 ,
     R.drawable.p83 ,
     R.drawable.p84 ,
     R.drawable.p85 ,
     R.drawable.p86 ,
     R.drawable.p87 ,
     R.drawable.p88 ,
     R.drawable.p89 ,
     R.drawable.p90 ,
     R.drawable.p91 ,
     R.drawable.p92 ,
     R.drawable.p93 ,
     R.drawable.p94 ,
     R.drawable.p95 ,
     R.drawable.p96 ,
     R.drawable.p97 ,
     R.drawable.p98 ,
     R.drawable.p99 ,
     R.drawable.p100 ,
     R.drawable.p101 ,
     R.drawable.p102 ,
     R.drawable.p103 ,
     R.drawable.p104 ,
     R.drawable.p105 ,
     R.drawable.p106 ,
     R.drawable.p107 ,
     R.drawable.p108 ,
     R.drawable.p109 ,
     R.drawable.p110 ,
     R.drawable.p111 ,
     R.drawable.p112 ,
     R.drawable.p113 ,
     R.drawable.p114 ,
     R.drawable.p115 ,
     R.drawable.p116 ,
     R.drawable.p117 ,
     R.drawable.p118 ,
     R.drawable.p119 ,
     R.drawable.p120 ,
     R.drawable.p121 ,
     R.drawable.p122 ,
     R.drawable.p123 ,
     R.drawable.p124 ,
     R.drawable.p125 ,
     R.drawable.p126 ,
     R.drawable.p127 ,
     R.drawable.p128 ,
     R.drawable.p129 ,
     R.drawable.p130 ,
     R.drawable.p131 ,
     R.drawable.p132 ,
     R.drawable.p133 ,
     R.drawable.p134 ,
     R.drawable.p135 ,
     R.drawable.p136 ,
     R.drawable.p137 ,
     R.drawable.p138 ,
     R.drawable.p139 ,
     R.drawable.p140 ,
     R.drawable.p141 ,
     R.drawable.p142 ,
     R.drawable.p143 ,
     R.drawable.p144 ,
     R.drawable.p145 ,
     R.drawable.p146 ,
     R.drawable.p147 ,
     R.drawable.p148 ,
     R.drawable.p149 ,
     R.drawable.p150 ,
     R.drawable.p151 ,
     R.drawable.p152 ,
     R.drawable.p153 ,
     R.drawable.p154 ,
     R.drawable.p155 ,
     R.drawable.p156 ,
     R.drawable.p157 ,
     R.drawable.p158 ,
     R.drawable.p159 ,
     R.drawable.p160 ,
     R.drawable.p161 ,
     R.drawable.p162 ,
     R.drawable.p163 ,
     R.drawable.p164 ,
     R.drawable.p165 ,
     R.drawable.p166 ,
     R.drawable.p167 ,
     R.drawable.p168 ,
     R.drawable.p169 ,
     R.drawable.p170 ,
     R.drawable.p171 ,
     R.drawable.p172 ,
     R.drawable.p173 ,
     R.drawable.p174 ,
     R.drawable.p175 ,
     R.drawable.p176 ,
     R.drawable.p177 ,
     R.drawable.p178 ,
     R.drawable.p179 ,
     R.drawable.p180 ,
     R.drawable.p181 ,
     R.drawable.p182 ,
     R.drawable.p183 ,
     R.drawable.p184 ,
     R.drawable.p185 ,
     R.drawable.p186 ,
     R.drawable.p187 ,
     R.drawable.p188 ,
     R.drawable.p189 ,
     R.drawable.p190 ,
     R.drawable.p191 ,
     R.drawable.p192 ,
     R.drawable.p193 ,
     R.drawable.p194 ,
     R.drawable.p195 ,
     R.drawable.p196 ,
     R.drawable.p197 ,
     R.drawable.p198 ,
     R.drawable.p199 ,
     R.drawable.p200 ,
     R.drawable.p201 ,
     R.drawable.p202 ,
     R.drawable.p203 ,
     R.drawable.p204 ,
     R.drawable.p205 ,
     R.drawable.p206 ,
     R.drawable.p207 ,
     R.drawable.p208 ,
     R.drawable.p209 ,
     R.drawable.p210 ,
     R.drawable.p211 ,
     R.drawable.p212 ,
     R.drawable.p213 ,
     R.drawable.p214 ,
     R.drawable.p215 ,
     R.drawable.p216

    };

    int[] getPatterns(){
        return patterns;
    }

    int getPatternsAt(int index){
        return  patterns[index];
    }

    int countPatterns(){
        return patterns.length ;
    }


    //Patterns
    private int miniPatterns[] = new int[]{

            R.drawable.pp1 ,
            R.drawable.pp2 ,
            R.drawable.pp3 ,
            R.drawable.pp4 ,
            R.drawable.pp5 ,
            R.drawable.pp6 ,
            R.drawable.pp7 ,
            R.drawable.pp8 ,
            R.drawable.pp9 ,
            R.drawable.pp10 ,
            R.drawable.pp11 ,
            R.drawable.pp12 ,
            R.drawable.pp13 ,
            R.drawable.pp14 ,
            R.drawable.pp15 ,
            R.drawable.pp16 ,
            R.drawable.pp17 ,
            R.drawable.pp18 ,
            R.drawable.pp19 ,
            R.drawable.pp20 ,
            R.drawable.pp21 ,
            R.drawable.pp22 ,
            R.drawable.pp23 ,
            R.drawable.pp24 ,
            R.drawable.pp25 ,
            R.drawable.pp26 ,
            R.drawable.pp27 ,
            R.drawable.pp28 ,
            R.drawable.pp29 ,
            R.drawable.pp30 ,
            R.drawable.pp31 ,
            R.drawable.pp32 ,
            R.drawable.pp33 ,
            R.drawable.pp34 ,
            R.drawable.pp35 ,
            R.drawable.pp36 ,
            R.drawable.pp37 ,
            R.drawable.pp38 ,
            R.drawable.pp39 ,
            R.drawable.pp40 ,
            R.drawable.pp41 ,
            R.drawable.pp42 ,
            R.drawable.pp43 ,
            R.drawable.pp44 ,
            R.drawable.pp45 ,
            R.drawable.pp46 ,
            R.drawable.pp47 ,
            R.drawable.pp48 ,
            R.drawable.pp49 ,
            R.drawable.pp50 ,
            R.drawable.pp51 ,
            R.drawable.pp52 ,
            R.drawable.pp53 ,
            R.drawable.pp54 ,
            R.drawable.pp55 ,
            R.drawable.pp56 ,
            R.drawable.pp57 ,
            R.drawable.pp58 ,
            R.drawable.pp59 ,
            R.drawable.pp60 ,
            R.drawable.pp61 ,
            R.drawable.pp62 ,
            R.drawable.pp63 ,
            R.drawable.pp64 ,
            R.drawable.pp65 ,
            R.drawable.pp66 ,
            R.drawable.pp67 ,
            R.drawable.pp68 ,
            R.drawable.pp69 ,
            R.drawable.pp70 ,
            R.drawable.pp71 ,
            R.drawable.pp72 ,
            R.drawable.pp73 ,
            R.drawable.pp74 ,
            R.drawable.pp75 ,
            R.drawable.pp76 ,
            R.drawable.pp77 ,
            R.drawable.pp78 ,
            R.drawable.pp79 ,
            R.drawable.pp80 ,
            R.drawable.pp81 ,
            R.drawable.pp82 ,
            R.drawable.pp83 ,
            R.drawable.pp84 ,
            R.drawable.pp85 ,
            R.drawable.pp86 ,
            R.drawable.pp87 ,
            R.drawable.pp88 ,
            R.drawable.pp89 ,
            R.drawable.pp90 ,
            R.drawable.pp91 ,
            R.drawable.pp92 ,
            R.drawable.pp93 ,
            R.drawable.pp94 ,
            R.drawable.pp95 ,
            R.drawable.pp96 ,
            R.drawable.pp97 ,
            R.drawable.pp98 ,
            R.drawable.pp99 ,
            R.drawable.pp100 ,
            R.drawable.pp101 ,
            R.drawable.pp102 ,
            R.drawable.pp103 ,
            R.drawable.pp104 ,
            R.drawable.pp105 ,
            R.drawable.pp106 ,
            R.drawable.pp107 ,
            R.drawable.pp108 ,
            R.drawable.pp109 ,
            R.drawable.pp110 ,
            R.drawable.pp111 ,
            R.drawable.pp112 ,
            R.drawable.pp113 ,
            R.drawable.pp114 ,
            R.drawable.pp115 ,
            R.drawable.pp116 ,
            R.drawable.pp117 ,
            R.drawable.pp118 ,
            R.drawable.pp119 ,
            R.drawable.pp120 ,
            R.drawable.pp121 ,
            R.drawable.pp122 ,
            R.drawable.pp123 ,
            R.drawable.pp124 ,
            R.drawable.pp125 ,
            R.drawable.pp126 ,
            R.drawable.pp127 ,
            R.drawable.pp128 ,
            R.drawable.pp129 ,
            R.drawable.pp130 ,
            R.drawable.pp131 ,
            R.drawable.pp132 ,
            R.drawable.pp133 ,
            R.drawable.pp134 ,
            R.drawable.pp135 ,
            R.drawable.pp136 ,
            R.drawable.pp137 ,
            R.drawable.pp138 ,
            R.drawable.pp139 ,
            R.drawable.pp140 ,
            R.drawable.pp141 ,
            R.drawable.pp142 ,
            R.drawable.pp143 ,
            R.drawable.pp144 ,
            R.drawable.pp145 ,
            R.drawable.pp146 ,
            R.drawable.pp147 ,
            R.drawable.pp148 ,
            R.drawable.pp149 ,
            R.drawable.pp150 ,
            R.drawable.pp151 ,
            R.drawable.pp152 ,
            R.drawable.pp153 ,
            R.drawable.pp154 ,
            R.drawable.pp155 ,
            R.drawable.pp156 ,
            R.drawable.pp157 ,
            R.drawable.pp158 ,
            R.drawable.pp159 ,
            R.drawable.pp160 ,
            R.drawable.pp161 ,
            R.drawable.pp162 ,
            R.drawable.pp163 ,
            R.drawable.pp164 ,
            R.drawable.pp165 ,
            R.drawable.pp166 ,
            R.drawable.pp167 ,
            R.drawable.pp168 ,
            R.drawable.pp169 ,
            R.drawable.pp170 ,
            R.drawable.pp171 ,
            R.drawable.pp172 ,
            R.drawable.pp173 ,
            R.drawable.pp174 ,
            R.drawable.pp175 ,
            R.drawable.pp176 ,
            R.drawable.pp177 ,
            R.drawable.pp178 ,
            R.drawable.pp179 ,
            R.drawable.pp180 ,
            R.drawable.pp181 ,
            R.drawable.pp182 ,
            R.drawable.pp183 ,
            R.drawable.pp184 ,
            R.drawable.pp185 ,
            R.drawable.pp186 ,
            R.drawable.pp187 ,
            R.drawable.pp188 ,
            R.drawable.pp189 ,
            R.drawable.pp190 ,
            R.drawable.pp191 ,
            R.drawable.pp192 ,
            R.drawable.pp193 ,
            R.drawable.pp194 ,
            R.drawable.pp195 ,
            R.drawable.pp196 ,
            R.drawable.pp197 ,
            R.drawable.pp198 ,
            R.drawable.pp199 ,
            R.drawable.pp200 ,
            R.drawable.pp201 ,
            R.drawable.pp202 ,
            R.drawable.pp203 ,
            R.drawable.pp204 ,
            R.drawable.pp205 ,
            R.drawable.pp206 ,
            R.drawable.pp207 ,
            R.drawable.pp208 ,
            R.drawable.pp209 ,
            R.drawable.pp210 ,
            R.drawable.pp211 ,
            R.drawable.pp212 ,
            R.drawable.pp213 ,
            R.drawable.pp214 ,
            R.drawable.pp215 ,
            R.drawable.pp216

    };

    int[] getMiniPatterns(){
        return miniPatterns;
    }

    int getMiniPatternsAt(int index){
        return  miniPatterns[index];
    }

    int countMiniPatterns(){
        return miniPatterns.length ;
    }


}
