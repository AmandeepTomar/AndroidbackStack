## Lifecycle of Activity and Fragment 

- Here we discussed about the lifecycle methods of activity and Fragments. 

### UseCase1 (Activity Single)
- When we launch app , 
    `onCreate()` , `onStart()` and `onResume()` called.
- Now Press Home Button 
    - `onPause()` and `onStop()` Will be called 
    
- Now Again Launch the App
    - `onRestart()` , `onStart()` and `onResume()` Will be called.
    
- Now click on system back button 
    - `onPause()`, `onStop()` and `onDestroy()` will be called. 
- What happen when activity is open and you get a call
    - `onPause()` , `onStop()`  will called.
    - As You cut the call `onRestart()` , `onStart()` and `onResume()`  will called 

### UseCase1 (Move Activity1 to Activity2)
- Activity1 `( OnCreate() , onStart() onResume())`
  - Click on Button and start new Activity2
- Activity1 `( onPause() ,  )`
- Activity2 `(onCreate() , onStart() , onResume())`
- Activity1 `( onStop())`
- Activity1 to Activity2
```kotlin
Activity1
    OnCreate()
    onStart()
    onResume()
    onPause()
Activity2
    onCreate()
    onStart()
    onResume()
Activity1
    onStop()
```
    
Sequence is like 
  - `onCreate(), onStart() and onResume()` of Activity1 ,
    -  then as we click on Button `onPause() of Activity1`
    -  `onCreate() , onStart() and onResume()` of Activity2 
    - `onStop()` of Activity1.
- <B>Now click back button on Activity2.</B>
    - `onPause()` onActivity2
    - `onRestart(), onStart(), onResume()` on Activity1.
    - `onStop(), onDestroy()` on Activity2.
  
```kotlin
Activity2
    onPause()
Activity1
    onRestart()
    onStart()
    onResume()
Activity2
    onStop()
    onDestroy()
```

## Fragment LifeCycle

- Caution: Avoid using the <fragment> tag to add a fragment using XML, as the <fragment> tag allows a fragment to move beyond the state of its FragmentManager. Instead, always use FragmentContainerView for adding a fragment using XML.
- When your fragment reaches the CREATED state, it has been added to a FragmentManager and the onAttach() method has already been called.

```kotlin
    onAttach()
    onCreate()
    onCreateView()
    onViewCreated()
    onViewStateRestored()
    onStart()
    onResume()
    onPause()
    onStop()
    onSavedIntance()
    onDestroyView()
    onDestroy()
    onDetach()

```
## UseCase of Activity and Fragment together.
```kotlin
Activity onCreate()
                    Fragment    onAttach()
                                onCreate()
                                onCreateView()
                                onViewCreated()
                                onViewSavedRestored()
                                onStart()
Activity onStart()
         onResume()
                    Fargment    onResume()
 Now we Back press on fragment that has addBackStack()
                    Fragment    onPause()
                                onStop()
                                onSavedInstance()
                                onDestroyView()
                                onDestroy()
                                onDetach()

```

## UseCase of Activity has a fragment and launch Another activity with Fragment together.


