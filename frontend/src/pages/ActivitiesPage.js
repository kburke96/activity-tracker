import './ActivitiesPage.scss';
import { React, useEffect, useState } from 'react';
import { ActivitySmallCard } from '../components/ActivitySmallCard';

export const ActivitiesPage = () => {
    // const [activity, setActivity] = useState({activities: []});
    const [activity, setActivity] = useState([]);

    useEffect(
        () => {
            const fetchActivities = async () => {
                const response = await fetch('http://localhost:8080/activities');
                const data = await response.json();
                // console.log("Data var is: " + data[0]);
                // console.log("Response var is: " + response)
                console.log(data);
                setActivity(data);
            };

            fetchActivities();
            console.log("Type of activity is: " + typeof(activity))
            // console.log("Data is: " + activity[0]);

        }, []
        
    );

    return (
        <div className="ActivitiesPage">
            <h1>Activities</h1>
            {/* <ActivitySmallCard activity={activity[0]}/>
            <ActivitySmallCard activity={activity[1]}/>
            <ActivitySmallCard activity={activity[2]}/>
            <ActivitySmallCard activity={activity[3]}/> */}
            
            {/* { activity.id.map(a => <ActivitySmallCard activity={a} />)  } */}
            
            
            {activity.map(activity => <ActivitySmallCard activity={activity} />)}
        </div>
    );
}