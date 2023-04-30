package com.handyman.Handyman.commons;

public interface UseCase<INPUT,OUTPUT> {
    OUTPUT execute(INPUT input);
}
