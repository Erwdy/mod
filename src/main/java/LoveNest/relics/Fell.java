package LoveNest.relics;

import LoveNest.helpers.ModHelper;
import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class Fell extends CustomRelic {
    public static final String ID = ModHelper.makePath("Fell");
    // 图片路径（大小128x128，可参考同目录的图片）
    private static final String IMG_PATH = "LoveNestResource/img/relics/Fell.png";
    // 遗物未解锁时的轮廓。可以不使用。如果要使用，取消注释
    private static final String OUTLINE_PATH = "LoveNestResource/img/relics/Fell_Outline.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Fell() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
        this.counter = 6;
        // 如果你需要轮廓图，取消注释下面一行并注释上面一行，不需要就删除
        // super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    @Override
    public AbstractRelic makeCopy() {
        return new Fell();
    }
    @Override
    public void setCounter(int setCounter) {
        this.counter = setCounter;
        if (this.counter == -2) {
            usedUp();
            this.counter = -2;
        }
    }



    @SpirePatch(
            clz = MapRoomNode.class,
            method = "wingedIsConnectedTo"
    )
    public static class WingedIsConnectedTo {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn<Boolean> Insert(MapRoomNode _instance, MapRoomNode node) {
            ArrayList<MapEdge> edges = _instance.getEdges();
            for (MapEdge edge : edges) {
                if (node.y == edge.dstY && AbstractDungeon.player.hasRelic("LoveNest:Fell") && (AbstractDungeon.player.getRelic("LoveNest:Fell")).counter > 0) {
                    return SpireReturn.Return(true);
                }
            }
            return SpireReturn.Continue();
        }
    }
    @SpirePatch(
            clz = MapRoomNode.class,
            method = "update"
    )
    public static class MapRoomNodeUpdate {
        @SpireInsertPatch(rloc = 72, localvars = {"normalConnection", "wingedConnection"})
        public static SpireReturn<Boolean> Insert(MapRoomNode _instance, boolean normalConnection, boolean wingedConnection) {
            if (!normalConnection && wingedConnection && AbstractDungeon.player.hasRelic("LoveNest:Fell")) {
                (AbstractDungeon.player.getRelic("LoveNest:Fell")).counter--;
                if ((AbstractDungeon.player.getRelic("LoveNest:Fell")).counter <= 0) {
                    AbstractDungeon.player.getRelic("LoveNest:Fell").setCounter(-2);
                }
            }
            return SpireReturn.Continue();
        }
    }
}