import './AddNewActivityPage.scss';
import React, { useState } from 'react';
import axios from 'axios';

export const AddNewActivityPage = () => {
    const [id, setId] = useState('');
    const [activityType, setActivityType] = useState('');
    const [activityName, setActivityName] = useState('');
    const [time, setTime] = useState('');
    const [distance, setDistance] = useState('');
    const [activityDate, setActivityDate] = useState('');
    // const [activityObj, setActivity] = useState({
    //     id: -1,
    //     activityType: '',
    //     activityName: '',
    //     time: '',
    //     distance: '',
    //     activityDate: ''
    // });

    const handleIdChange = event => {
        setId(event.target.value)
    };

    const handleActivityTypeChange = event => {
        setActivityType(event.target.value)
    };

    const handleActivityNameChange = event => {
        setActivityName(event.target.value)
    };

    const handleTimeChange = event => {
        setTime(event.target.value)
    };

    const handleDistanceChange = event => {
        setDistance(event.target.value)
    };

    const handleActivityDateChange = event => {
        setActivityDate(event.target.value)
    };

    const handleSubmit = event => {
        event.preventDefault();
        alert(`Your state values: \n 
              id: ${id} \n 
              name: ${activityName} \n
              You can replace this alert with your process`);
        const dataObject = {
            id,
            activityType,
            activityName,
            time,
            distance,
            activityDate
        };
        // axios.put('http://localhost:8080/activity', mockData)
        //     .then(res => {
        //         console.log(res);
        //         console.log(res.data);
        //     })

        axios.put('http://localhost:8080/activity', dataObject)
            .then(res => {
                console.log(res);
            })

    };

    return (
        <div>
            <h1>Add a new Activity</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Activity ID: </label>
                    <input
                        type="text"
                        name="id"
                        placeholder="Enter ID"
                        onChange={handleIdChange}
                        value={id}
                    />
                    <br />
                    <label>Activity Type: </label>
                    <input
                        type="text"
                        name="type"
                        placeholder="Type"
                        onChange={handleActivityTypeChange}
                        value={activityType}
                    />
                    <br />
                    <label>Activity Name: </label>
                    <input
                        type="text"
                        name="activityName"
                        placeholder="Name"
                        onChange={handleActivityNameChange}
                        value={activityName}
                    />
                    <br />
                    <label>Activity Time: </label>
                    <input
                        type="text"
                        name="time"
                        placeholder="Time"
                        onChange={handleTimeChange}
                        value={time}
                    />
                    <br />
                    <label>Activity Distance: </label>
                    <input
                        type="text"
                        name="distance"
                        placeholder="Distance"
                        onChange={handleDistanceChange}
                        value={distance}
                    />
                    <br />
                    <label>Activity Date: </label>
                    <input
                        type="text"
                        name="date"
                        placeholder="Date"
                        onChange={handleActivityDateChange}
                        value={activityDate}
                    />
                </div>
                <input type="submit" value="Add Activity"></input>
                {/* Submit
            </button> */}
            </form>
        </div>
    )
}
