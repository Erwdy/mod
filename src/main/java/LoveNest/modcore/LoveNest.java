package LoveNest.modcore;//为什么要来视奸我的mod...算了，看就看吧，反正也是垃圾代码

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import LoveNest.Characters.Qing;
import LoveNest.card.*;
import LoveNest.card.MultipleChop;

import LoveNest.relics.Fell;

import static LoveNest.Characters.Qing.PlayerColorEnum.*;

@SpireInitializer
public class LoveNest implements EditCardsSubscriber, EditStringsSubscriber , EditCharactersSubscriber , EditRelicsSubscriber { // 实现接口
    // 人物选择界面按钮的图片
    private static final String MY_CHARACTER_BUTTON = "LoveNestResource/img/char/Character_Button.png";
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = "LoveNestResource/img/char/Character_Portrait.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "LoveNestResource/img/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "LoveNestResource/img/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "LoveNestResource/img/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "LoveNestResource/img/char/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "LoveNestResource/img/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "LoveNestResource/img/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "LoveNestResource/img/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "LoveNestResource/img/char/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = "LoveNestResource/img/char/cost_orb.png";
    public static final Color MY_COLOR = new Color(248.0F / 255.0F, 32.0F / 255.0F, 132.0F / 255.0F, 1.0F);
    public LoveNest() {
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件

        BaseMod.addColor(LOVENEST_PINK, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENEYGY_ORB, BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);
    }

    public static void initialize() {
        new LoveNest();
    }

    // 当basemod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {
        // TODO 这里写添加你卡牌的代码
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Protect());
        BaseMod.addCard(new VIII());
        BaseMod.addCard(new MultipleChop());
        BaseMod.addCard(new Offensive());
        BaseMod.addCard(new Energy());
        BaseMod.addCard(new NotDemon());
        BaseMod.addCard(new HeavyChop());
        BaseMod.addCard(new Confused());
        BaseMod.addCard(new Behead());
        BaseMod.addCard(new PsychologicalComfort());
        BaseMod.addCard(new Qing1());
        BaseMod.addCard(new Appease());
    }

    @Override
    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "LoveNestResources/localization/" + lang + "/cards.json"); // 加载相应语言的卡牌本地化内容。
        // 如果是中文，加载的就是"LoveNestResources/localization/ZHS/cards.json"
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "LoveNestResources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "LoveNestResources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "LoveNestResources/localization/" + lang + "/powers.json");
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Qing(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, MY_CHARACTER);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new Fell(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
    }
}

