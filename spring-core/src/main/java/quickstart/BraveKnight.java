package quickstart;

public class BraveKnight implements Knight {

    private Quest quest;

    // 通过 DI，对象的依赖关系将由系统中负责协各对象的第三方组件在创建对象时进行设定。
    public BraveKnight(Quest quest) {// Quest 被注入进来
        this.quest = quest;
    }

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }
}
