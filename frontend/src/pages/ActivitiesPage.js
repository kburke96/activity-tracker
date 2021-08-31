import './ActivitiesPage.scss';
import { React, useEffect, useState } from 'react';
import { ActivitySmallCard } from '../components/ActivitySmallCard';
import { Navbar } from '../components/Navbar';

export const ActivitiesPage = () => {
    // const [activity, setActivity] = useState({activities: []});
    const [activity, setActivity] = useState([]);

    useEffect(
        () => {
            const fetchActivities = async () => {
                const response = await fetch('http://localhost:8080/activities');
                const data = await response.json();

                console.log(data);
                setActivity(data.content);
            };

            fetchActivities();
            console.log("Type of activity is: " + typeof(activity))
        }, []
        
    );

    return (
        <div className="ActivitiesPage">
            <h1>Activities</h1>
            <Navbar />
            
            
            {activity.map(activity => <ActivitySmallCard activity={activity} />)}
        </div>
    );
}