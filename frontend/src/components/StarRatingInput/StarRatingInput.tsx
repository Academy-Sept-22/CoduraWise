import React, { Dispatch, SetStateAction } from "react";
import "./StarRatingInput.scss";

interface RatingSelectionProps {
  setRating: Dispatch<SetStateAction<string>>;
}

const StarRatingInput = ({ setRating }: RatingSelectionProps) => {
  const handleOnChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRating(event.target.value);
  };

  return (
    <div className="rate">
      <input
        type="radio"
        id="star5"
        name="rate"
        value="5"
        onChange={handleOnChange}
      />
      <label htmlFor="star5" title="text">
        5 stars
      </label>
      <input
        type="radio"
        id="star4"
        name="rate"
        value="4"
        onChange={handleOnChange}
      />
      <label htmlFor="star4" title="text">
        4 stars
      </label>
      <input
        type="radio"
        id="star3"
        name="rate"
        value="3"
        onChange={handleOnChange}
      />
      <label htmlFor="star3" title="text">
        3 stars
      </label>
      <input
        type="radio"
        id="star2"
        name="rate"
        value="2"
        onChange={handleOnChange}
      />
      <label htmlFor="star2" title="text">
        2 stars
      </label>
      <input
        type="radio"
        id="star1"
        name="rate"
        value="1"
        onChange={handleOnChange}
      />
      <label htmlFor="star1" title="text">
        1 star
      </label>
    </div>
  );
};

export default StarRatingInput;

