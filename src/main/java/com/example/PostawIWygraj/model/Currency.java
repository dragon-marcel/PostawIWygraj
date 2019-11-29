package com.example.PostawIWygraj.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ArrayOfCenaZslota" )
public class Currency {
    public Currency() {

    }
    @XmlAttribute(name="CenaZlota")
    private String valuta ;
    
    
}
