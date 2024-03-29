## Lifecycle of Activity and Fragment 
- Lifecycle of Activity
- Lifecycle of Fragments
- Fragments backstack
- Parcelable question
- Interface delegates exampole added in MainActivity

### This repo contains or usefull for.
- Activity Lifecycle related questions
    - When activity launch , press home button , when we receive call 
    - When we move Activity one to Activity2 , on BackPress.

- Fragment lifecycle
    - Fragment lifecycle methods 
    - Fragments with Activity lifecycyle 
    - Activity with fragment and launch another activity with fragment 
    - Activity has 4 fragments 
    - Fragments `replace()`  and `add()` method diffence and behaviour on lifecycle.

- Share data between Activity1 to Activity2 using object with `Parcelable` Interface with customization.  

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

### Q:  What is happen when we use addToBackStack() with replace() and without addToBackStack() which life cycle method will be called.
- UseCase Fragment1 added with`replace()` with `addToBackStack()` and Fragment2 with `replace()` and without `addToBackStack()` , Which life cycle of both fragment called.
    
```kotlin
    Fragment1   onAttach()
                onCreate()
                onCreateView()
                onViewCreated()
                onViewStateRestored()
                onStart()
                onResume()
    // click on replace button without addToBackStack
    Fragment1   onPause()
                onStop()
    Fragment2   onAttach()
                onCreate()
                onCreateView()
                onViewCreated()
                onViewStateRestored()
                onStart()
    Fragment1   onDestroyView()
    Fragment2   onResume()
    // onBackPress 
    Fragment1   onDestroy()
                onDetach()
    // And still fragment 2 is visible.
```

### Q: addToBackStack stores transaction.  
- By calling addToBackStack(), the replace transaction is saved to the back stack so the user can reverse the transaction and bring back the previous fragment by pressing the Back button.

If you add multiple changes to the transaction (such as another add() or remove()) and call addToBackStack(), then all changes applied before you call commit() are added to the back stack as a single transaction and the Back button will reverse them all together.

The order in which you add changes to a FragmentTransaction doesn't matter, except:

You must call commit() last. If you're adding multiple fragments to the same container, then the order in which you add them determines the order they appear in the view hierarchy.

So you have to commit at the last.




## Shared data Between Two Activity 
- ActivityA wants to shared data to ActivityB 
- We use `Parcelable` interface to share the object between two activities. 
- Benefit of using `Parcelable` interface is that we have complete control on which data need to sent or not.
   -  Suppose we have an object that have three property
        - String name , isPass : Boolean = false and isNot:Boolean=false
        - in that case we just want to name should be parcelable so just name in `writeToParcel()` method and also in constructor we used name dor the read. only this value will be shared.
        - <B>What will happen with isPass and isNot<B>
            - Answer is name has the actual value and isPass and isNot will have default values.
            - If we do not writeToParcel() the property that will not be shared , it will shared with Default value.
    ```kotlin
        data class ShareDataWithParcelable(val data: String?, val isData: Boolean =false, val isNotData:Boolean=false) : Parcelable {
            constructor(parcel: Parcel) : this(
                parcel.readString()
        //       parcel.readBoolean(),
        //        parcel.readBoolean()
        
            ) {
            }
        
            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(data)
        //        parcel.writeBoolean(isData)
        //        parcel.writeBoolean(isNotData)
        }
        
            override fun describeContents(): Int {
                return 0
            }
        
            companion object CREATOR : Parcelable.Creator<ShareDataWithParcelable> {
                override fun createFromParcel(parcel: Parcel): ShareDataWithParcelable {
                    return ShareDataWithParcelable(parcel)
                }
        
                override fun newArray(size: Int): Array<ShareDataWithParcelable?> {
                    return arrayOfNulls(size)
                }
            }
        }
  
  // Code in Activity A
             val data=ShareDataWithParcelable("Yes We get this",true,true)
            val intent=Intent(this,ActivityB::class.java)
            val bundle=Bundle()
            bundle.putParcelable("Bundle",data)
            intent.putExtra("Data",bundle);
            startActivity(intent)
  // Code in ActivityB
  
            intent?.let {
            val bundle = it.extras
            val data= bundle?.get("Data") as Bundle
            Log.e(TAG, "onCreate: Bundle data ${data}" ) // Bundle
            Log.e(TAG, "onCreate: Bundle data ${data["Bundle"]}" ) // data
        }
        
    output :  Bundle data Bundle[mParcelledData.dataSize=212]
         Bundle data ShareDataWithParcelable(data=Yes We get this, isData=false, isNotData=false)

    ```


### Interface Delegates 
- Interface delegate is a design pattern in kotlin that provide the composion over the abstraction. 
- We can reduce the complexity of abstraction, example we have a baseclass that provide the logging for analytocs, or we have another base activity that having the funcationality of deeplink so we have two activity. to reduce the nested abstraction we can help the delegate.

```kotlin

class ActivityA : AppCompatActivity(),
    LoggingState by LoggingStateImpl(),
    Deeplink by DeeplinkImpl() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerLifeCycleOwner(this)
        handleDeeplink(this, intent)
    }
    }

interface LoggingState {
    fun registerLifeCycleOwner(owner: LifecycleOwner)
}

class LoggingStateImpl : LoggingState, LifecycleEventObserver {
    override fun registerLifeCycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> Log.e("TAG", "onStateChanged: onResumed Called")
            Lifecycle.Event.ON_PAUSE -> Log.e("TAG", "onStateChanged: onPause Called")
            else -> Unit
        }
    }

}

// Deligate for DeepLink

interface Deeplink {
    fun handleDeeplink(activityA: ComponentActivity, intent: Intent)
}

class DeeplinkImpl : Deeplink {
    override fun handleDeeplink(activityA: ComponentActivity, intent: Intent) {
        Log.e("TAG", "handleDeeplink: perfomr deeplink logic $activityA $intent")
    }

}
```
