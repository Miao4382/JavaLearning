- Generics
  - why `List<int> arr = new ArrayList<>();` caused error?
    - Because of type erasure. At compile time, all the type parameters in generic types are replaced with their bounds or `Object` (if the type parameters are unbounded). However, `int` is primitive type, not a referenced type, it can not be replaced by the bound type or `Object`.

- `<T extends E>`: the generic type `T` can be any type that is `E` or subclass of `E`, or the upper bound of type `T` is `E`.
- `<? extends E>`: `?` is a wildcard, it can be any type that is `E` or subclass of `E`, or the upper bound of the generic type is `E`. In `<T extends E>`, we used the placeholder `T`, so we can refer to this generic type again, but we cannot use `?` to refer to a specific type.
- `<? super T>`: the generic type `?` can be any type that is `T` or supertypes of `T`, or the lower bound of the generic type is `T`.
