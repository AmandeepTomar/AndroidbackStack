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

### UseCase Activity3 has many fragments with add and replace methods. 
- `add()` it just add the fragment on top of another fragment
- `replace()` it first replace all the fragment then add new fragment. 
#### its like we have an activity that has four fragment.
- first add with `replace()` and addedToBackStack()
- Second add with `replace()` and addedToBackStack()
- third add with `add()` no backstack
- fourth add with `replace()` and addedToBackStack()

```kotlin
    // As we launch the activity all fragment need to be added 
    // so its look like 
    Activity    onCreate()
                        Fragment1   onAttach()
                                    onCreate()
                                    onCraeteView()
                                    onViewCreated()
                                    onViewStateRestored()
                        Fragment2   onAttach()
                                    onCreate()
                                    onCraeteView()
                                    onViewCreated()
                                    onViewStateRestored()
                        Fragment3   onAttach()
                                    onCreate()
                                    onCraeteView()
                                    onViewCreated()
                                    onViewStateRestored()
                        Fragment4   onAttach()
                                    onCreate()
                                    onCraeteView()
                                    onViewCreated()
                                    onViewStateRestored()
                        Fragment1   onStart()
                        Fragment2   onStart()
                        Fragment3   onStart()
                        Fragment4   onStart()
    Activity    onStart()
                onResume()
                        Fragment1   onResume()
                        Fragment2   onResume()
                        Fragment3   onResume()
                        Fragment4   onResume()

// Now press the system back button.
                        Fragment4   onPause()
                                    onStop() 
                                    onDestroyView()
                                    onDestroy()
                                    onDetach()
// same for Fragment 2 and 1 , 3 have not added in backstack.
// so fragment 3 is visible on screen after 3 back press.
// Now As we hit the back Button 
                        Fragment3   onPause()
    Activity    onPause()
                        Fragment3   onStop()
    Activity    onStop()
                        Fragment3   
                                    onDestroyView()
                                    onDestroy()
                                    onDetach()
    Activity    onDestroy

// Now we hit the Home Button.
                        Fragment1   onPause()
                        Fragment2   onPause()
                        Fragment3   onPause()
                        Fragment4   onPause()
    Activity    onPause()
                        Fragment1   onStop()
                        Fragment2   onStop()
                        Fragment3   onStop()
                        Fragment4   onStop()
    Activity    onStop()
                        Fragment1   onSaveInstanceState()
                        Fragment2   onSaveInstanceState()
                        Fragment3   onSaveInstanceState()
                        Fragment4   onSaveInstanceState()

// As launch again 
    onRestart()
    onStart() -> Fragments
    onStart()
    onResume()
    onResume()--> Fragments


```
#### Scenario one Launch Activity With Fragment 
```kotlin
Activity3   onCreate()
                    Fragment2   onAttach()
                                onCreate()
                                onCreateView()
                                onViewCreated()
                                onViewStateRestored()
                                onStart()
            onStart()
            onResume()
                    Fragment2   onResume()
//  Fragment2 using replace , now we add fragment3 using add 
 // No Method of Fragment2 Will call 
                    Fragment3   onAttatch()
                                onCreate()
                                onCreateView()
                                onViewCreated()
                                onViewStaterestored()
                                onStart()
                                onResume()
// Now Hit Back Button then all method of Fragment3 Will call 
// no method of Fragment2 will call.
                    Fragment3   onPause()
                                onStop()
                                onSavedInstance()
                                onDestroyView()
                                onDestroy()
                                onDetach()
//  Fragment2 using replace , now we add fragment4 using replace 
//  Fragment2 lifeCycle method called noe Fragment2 is in OnResume() STATE.
                    Fragment2   onPause()
                                onStop()
                    Fragment4   onAttatch()
                                onCreate()
                                onCreateView()
                                onViewCreated()
                                onViewSavedrestored()
                                onStart()
                                
                    Fragment2   onDestroyView()
                    Fragment4   onResume()
// Now Hit Back Button (Both Fragment using replace())
                    Fragment4   onPause()
                                onStop()
                    Fragment2   onCreateView()
                                onViewCreated()
                                onViewStateRestored()
                                onStart()
                    Fragment4   onDestroyView()
                                onDestroy()
                                onDetach()
                    Fragment2   onResume()

```

## Note 
- When we call `replace()` it will first replace all the fragments that are added and then added new fragment
    - for example we have add a 
      fragment using replace f1 ,
      then add f2 using f1 then replace 
        Now `replace()` will remove f1 and f2 and add f3.
      on backpress of f3 now both f1 and f2 will call onCreateView to on Resume.
      

## UseCase of Activity1 has a fragment1 and launch Another Activity2 with Fragment2 together.
- Sequences of the lifecycle events callbacks will be
```kotlin
    Activity1   onCreate()
            Fragment1   onAttach()
                        onCreate()
                        onCreateView()
                        onViewCreated()
                        onViewStateRestore()
                        onStart()
    Activity1   onStart()
                onResume()
            Fragment1   onResume()
    // Here we click on Button , for Activity2
                        onPause()
    Activity1   onPause()
    Activity2   onCreate()
            Fargement2  onAttach()
                        onCreate()
                        onCreateView()
                        onViewCreated()
                        onViewStateRestore()
                        onStart()
    Activity2   onStart()
                onResume()
            Fragment2   onResume()
            Fragment1   onStop()
                       
    Activity1   onStop()
            Fragment1   onSavedInstanceState()
    // Bow click on System back button This part is Important.
            Fragment2   onPause()
    Activity2   onPause()
                onRestart()
            Fragment1   onStart()
    Activity1   onStart()
                onResume()
            Fargment1   onResume()
            Fragment2   onStop()
    Activity2   onStop()
            Fargment2   onDestroyView()
                        onDestroy()
                        onDetach()
    Activity2   onDestroy()

```


