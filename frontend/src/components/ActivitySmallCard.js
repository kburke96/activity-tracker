import { React } from 'react';
import './ActivitySmallCard.scss';
import { Link } from 'react-router-dom';
// import { running } from 'running.png';

export const ActivitySmallCard = ({ activity }) => {


    if (!activity) {
        return (
            <p>Activity not found.</p>
        );
    }

    var activityDateParts = activity.activityDate.split(" ");
    var activityDate = activityDateParts[0];
    const isActivityCycling = activity.activityType === "Cycling";
    var logo = null;
    switch (activity.activityType) {
        case "Cycling":
            logo = "cycling.png";
            break;
        case "Running":
            logo = "running.png";
            break;
        case "Yoga":
            logo = "yoga.png";
            break;
        case "Cardio":
            logo = "cardio.png";
            break;
        case "Hiking":
            logo = "hiking.png";
            break;
        default:
            logo = null;
            break;
    }


    return (
        <Link to={{ pathname: `/activity/${activity.id}` }}>
            <div className="ActivitySmallCard">
                <div className={isActivityCycling ? 'cycling' : 'not-cycling'}>
                    <div className="logo">
                        <img src={logo} width="50" height="50"></img>
                    </div>

                    <table className="activity-table">
                        <tr>
                            <th>
                                <p className="activity-type-name">{activity.activityType}</p>
                            </th>
                            <th>
                                <p className="activity-distance">{activity.distance}km</p>
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <p className="activity-name">{activity.activityName} - {activityDate}</p>
                            </th>
                            <th>
                                <p className="activity-time">{activity.time}</p>
                            </th>
                        </tr>
                    </table>



                </div>
            </div>
        </Link>
    );
}