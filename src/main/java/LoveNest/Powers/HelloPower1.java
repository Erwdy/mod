package LoveNest.Powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HelloPower1 extends AbstractPower {
    public static final String POWER_ID = "Hello";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public HelloPower1(AbstractCreature owner, int cardAmt) {
        this.name = NAME;
        this.ID = "Hello";
        this.owner = owner;
        this.amount = cardAmt;
        this.updateDescription();
        this.loadRegion("hello");
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();

            for(int i = 0; i < this.amount; ++i) {
                this.addToBot(new MakeTempCardInHandAction(AbstractDungeon.getCard(CardRarity.UNCOMMON, AbstractDungeon.cardRandomRng).makeCopy(), 1, false));
            }
        }

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Hello");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
