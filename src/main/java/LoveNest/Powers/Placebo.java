package LoveNest.Powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import LoveNest.helpers.ModHelper;

public class Placebo extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = ModHelper.makePath("Placebo");
    // 能力的本地化字段
    private static PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Placebo(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;

        // 添加一大一小两张能力图
        String path128 = "LoveNestResource/img/powers/Example84.png";
        String path48 = "LoveNestResource/img/powers/Example32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }




               public void updateDescription() {
                   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];

    }

    public void atEndOfTurn(boolean isPlayer) {
        this.flash();

        this.addToBot(new HealAction(this.owner, this.owner, this.amount));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Placebo");

    }
}




