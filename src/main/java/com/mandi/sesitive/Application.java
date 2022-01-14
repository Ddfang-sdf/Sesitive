package com.mandi.sesitive;

import com.mandi.sesitive.annotation.*;
import javafx.scene.Parent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
        Child child = Sensitive.desensitize(new Child());
        System.out.println();
    }
}
