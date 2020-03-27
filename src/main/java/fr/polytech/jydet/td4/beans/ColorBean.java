package fr.polytech.jydet.td4.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class ColorBean implements Serializable {
    String backColor;
    String fontColor;
}
