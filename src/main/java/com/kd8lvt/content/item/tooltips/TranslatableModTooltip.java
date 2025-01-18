package com.kd8lvt.content.item.tooltips;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class TranslatableModTooltip extends ModTooltip {
    private String suffix = null;
    private TranslatableModTooltip(Text txt) {
        super(txt);
    }
    public TranslatableModTooltip(Identifier id,String suffix) {
        this(suffix);
        this.setId(id);
    }
    public TranslatableModTooltip(Identifier id) {
        this(Text.translatable(id.toTranslationKey("tooltip")));
        this.setId(id);
    }
    public TranslatableModTooltip(String suffix) {
        this();
        this.suffix=suffix;
    }
    public TranslatableModTooltip() {
        super(Text.empty());
    }

    @Override
    public void setId(Identifier id) {
        if (this.suffix != null) super.setId(id.withSuffixedPath(suffix));
        if (Objects.equals(this.text, Text.empty())) {
            text = Text.translatable(id.toTranslationKey("tooltip"));
        }
    }
}
